package com.jr.employee.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jr.controller.beans.EmployeeRequestBean;
import com.jr.controller.beans.EmployeeResponseBean;
import com.jr.employee.dao.service.EmployeeDAO;
import com.jr.employee.service.EmployeeService;
import com.jr.entity.Employee;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	final static Logger log = Logger.getLogger(EmployeeServiceImpl.class);

	@Autowired
	@Qualifier(value = "EmployeeDAO")
	private EmployeeDAO dao;

	public EmployeeDAO getDao() {
		return dao;
	}

	public void setDao(final EmployeeDAO dao) {
		this.dao = dao;
	}

	public String insertEmployee(final EmployeeRequestBean employee) {
		EmployeeServiceImpl.log.trace("EmployeeServiceImpl.insertEmployee() method entry with request " + employee);
		Employee emp = new Employee();
		if (employee == null) {
			EmployeeServiceImpl.log.debug("employee object is null" + employee);
			return "Null object received";
		}
		else {
			if (employee.getEmpId() > 0) emp.setEmpId(employee.getEmpId());
			else {
				EmployeeServiceImpl.log.debug("Invalid employee Id " + employee.getEmpId());
				return "Invalid employeeId";
			}
			if (employee.getDesg() == null || employee.getDesg().isEmpty()) {
				EmployeeServiceImpl.log.debug("Invalid Designation" + employee.getDesg());
				return "Invalid Designation";
			}
			else
				emp.setDesg(employee.getDesg());
			if (employee.getEname() == null || employee.getEname().isEmpty()) {
				EmployeeServiceImpl.log.debug("Invalid Empoyee Name" + employee.getEname());
				return "Invalid Empoyee Name";
			}
			else
				emp.setEname(employee.getEname());
			if (employee.getSalary() > 0 && employee.getSalary() < 30000) emp.setSalary(employee.getSalary());
			else {
				EmployeeServiceImpl.log.debug("Invalid Salary" + employee.getSalary());
				return "Invalid Salary";
			}
			dao.saveEmployee(emp);
		}

		EmployeeServiceImpl.log.trace("EmployeeServiceImpl.insertEmployee() method exit");
		return "Registration Successful";
	}

	public List<EmployeeResponseBean> getAllEmployees() {
		List<Employee> listEmployee = dao.fetchAllEmployees();
		List<EmployeeResponseBean> list = new ArrayList<EmployeeResponseBean>();
		for (Iterator<Employee> iterator = listEmployee.iterator(); iterator.hasNext();) {
			Employee employee = iterator.next();
			EmployeeResponseBean bean = new EmployeeResponseBean();
			bean.setEmpId(employee.getEmpId());
			bean.setEname(employee.getEname());
			bean.setDesg(employee.getDesg());
			bean.setSalary(employee.getSalary());
			list.add(bean);
		}
		return list;

	}

	public List<EmployeeResponseBean> getEmployeeByEmpId(final String empId) {
		List<Employee> listEmployee = dao.fetchEmloyeeByEmpId(empId);
		List<EmployeeResponseBean> employeeBean = new ArrayList<EmployeeResponseBean>();
		for (Iterator<Employee> iterator = listEmployee.iterator(); iterator.hasNext();) {
			Employee employee = iterator.next();
			EmployeeResponseBean bean = new EmployeeResponseBean();
			bean.setEname(employee.getEname());
			bean.setDesg(employee.getDesg());
			bean.setSalary(employee.getSalary());
			employeeBean.add(bean);
		}
		return employeeBean;
	}

}
