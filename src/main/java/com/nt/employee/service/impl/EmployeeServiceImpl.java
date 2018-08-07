package com.nt.employee.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nt.controller.EmployeeController;
import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.dao.service.EmployeeDAO;
import com.nt.employee.service.EmployeeService;
import com.nt.entity.Employee;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	final static Logger		log	= Logger.getLogger(EmployeeServiceImpl.class);

	@Autowired
	@Qualifier(value = "EmployeeDAO")
	private EmployeeDAO dao;

	public EmployeeDAO getDao() {
		return dao;
	}

	public void setDao(EmployeeDAO dao) {
		this.dao = dao;
	}

	public String insertEmployee(EmployeeRequestBean employee) {
		String msg = null;
		
		Employee emp = new Employee();
		if(employee==null)
		{
			log.debug("employee object is null"+employee);
		}
		else
		{
		emp.setEmpId(employee.getEmpId());
		emp.setDesg(employee.getDesg());
		emp.setEname(employee.getEname());
		emp.setSalary(employee.getSalary());
		msg = dao.saveEmployee(emp);
		}
		return msg;
	}

	public List<EmployeeRequestBean> getAllEmployees() {
		List<Employee> listEmployee=dao.fetchAllEmployees();
		List<EmployeeRequestBean> list=new ArrayList<EmployeeRequestBean>();
		for (Iterator iterator = listEmployee.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			EmployeeRequestBean bean=new EmployeeRequestBean();
			bean.setEmpId(employee.getEmpId());
			bean.setEname(employee.getEname());
			bean.setDesg(employee.getDesg());
			bean.setSalary(employee.getSalary());
			list.add(bean);			
		}
		return list;
		
	}

	public List<EmployeeRequestBean> getEmployeeByEmpId(String empId) {
		List<Employee> listEmployee=dao.fetchEmloyeeByEmpId(empId);
		List<EmployeeRequestBean> employeeBean=new ArrayList<EmployeeRequestBean>();
		for (Iterator iterator = listEmployee.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
		  EmployeeRequestBean bean=new EmployeeRequestBean();
		  bean.setEname(employee.getEname());
		  bean.setDesg(employee.getDesg());
		  bean.setSalary(employee.getSalary());
          employeeBean.add(bean);
		}
		return employeeBean;
	}

}
