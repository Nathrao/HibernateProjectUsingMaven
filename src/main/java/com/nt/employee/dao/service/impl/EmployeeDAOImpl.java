package com.nt.employee.dao.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.nt.employee.dao.service.EmployeeDAO;
import com.nt.entity.Employee;
import com.nt.util.HibernateUtil;

@Repository("EmployeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	private static SessionFactory factory = HibernateUtil.getSessionFactory();

	public static SessionFactory getFactory() {
		return EmployeeDAOImpl.factory;
	}

	public static void setFactory(final SessionFactory factory) {
		EmployeeDAOImpl.factory = factory;
	}

	public String saveEmployee(final Employee employee) {
		boolean isSuccess = true;
		try {
			Session session = EmployeeDAOImpl.factory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(employee);
			tx.commit();
		}
		catch (ConstraintViolationException e) {
			isSuccess = false;
		}
		if (isSuccess) return "Registration Successful";
		else
			return employee.getEmpId() + " is already available";
	}

	public List<Employee> fetchAllEmployees() {

		Session session = EmployeeDAOImpl.factory.openSession();
		Criteria crit = session.createCriteria(Employee.class);
		// List<Employee> list=crit.list();
		return crit.list();
	}

	public List<Employee> fetchEmloyeeByEmpId(final String empId) {

		Session session = EmployeeDAOImpl.factory.openSession();
		Criteria crit = session.createCriteria(Employee.class);
		// Criterion criterion=Restrictions.eq("empId", empId);
		crit.add(Restrictions.eq("empId", Integer.parseInt(empId)));
		// List list=crit.list();
		return crit.list();
	}

}
