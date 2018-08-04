package com.nt.employee.dao.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.nt.employee.dao.service.EmployeeDAO;
import com.nt.entity.Employee;
import com.nt.util.HibernateUtil;

@Repository("EmployeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	private SessionFactory factory;
	public String saveEmployee(Employee employee) {
		boolean isSuccess=true;
		try {
		factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		session.save(employee);
		Transaction tx=session.beginTransaction();
		tx.commit();
		}catch(ConstraintViolationException e) {
			isSuccess =false;
		}
		if(isSuccess)
		return "Registration Successful";
		else
		return employee.getEmpId()+" is already available" ;
	}
	public List<Employee> fetchAllEmployees() {
		factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		Criteria crit=session.createCriteria(Employee.class);
		//List<Employee> list=crit.list();
		return crit.list();
	}
	public List<Employee> fetchEmloyeeByEmpId(String empId) {
		factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		Criteria crit=session.createCriteria(Employee.class);
		//Criterion criterion=Restrictions.eq("empId", empId);
		crit.add(Restrictions.eq("empId", Integer.parseInt(empId)));
		//List list=crit.list();
		return crit.list();
	}

}
