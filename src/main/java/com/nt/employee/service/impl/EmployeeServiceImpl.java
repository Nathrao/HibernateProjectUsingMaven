package com.nt.employee.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.dao.service.EmployeeDAO;
import com.nt.employee.service.EmployeeService;
import com.nt.entity.Employee;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	final static Logger log = Logger.getLogger(EmployeeServiceImpl.class);

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
		log.trace("EmployeeServiceImpl.insertEmployee() method entry with request " + employee);
		Employee emp = new Employee();
		if (employee == null) {
			log.debug("employee object is null" + employee);
			return "Null object received";
		}
		else {
			if (employee.getEmpId() > 0) {
				emp.setEmpId(employee.getEmpId());
			}
			else {
				log.debug("Invalid employee Id " + employee.getEmpId());
				return "Invalid employeeId";
			}
			if (employee.getDesg() == null || employee.getDesg().isEmpty()) {
				log.debug("Invalid Designation" + employee.getDesg());
				return "Invalid Designation";
			}
			else {
				emp.setDesg(employee.getDesg());
			}
			if (employee.getEname() == null || employee.getEname().isEmpty()) {
				log.debug("Invalid Empoyee Name" + employee.getEname());
				return "Invalid Empoyee Name";
			}
			else {
				emp.setEname(employee.getEname());
			}
			if (employee.getSalary() > 0 && employee.getSalary() < 30000) {
				emp.setSalary(employee.getSalary());
			}
			else {
				log.debug("Invalid Salary" + employee.getSalary());
				return "Invalid Salary";
			}
			dao.saveEmployee(emp);
		}

		log.trace("EmployeeServiceImpl.insertEmployee() method exit");
		return 	"Registration Successful";
	}

	public List<EmployeeRequestBean> getAllEmployees() {
		List<Employee> listEmployee = dao.fetchAllEmployees();
		List<EmployeeRequestBean> list = new ArrayList<EmployeeRequestBean>();
		for (Iterator<Employee> iterator = listEmployee.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			EmployeeRequestBean bean = new EmployeeRequestBean();
			bean.setEmpId(employee.getEmpId());
			bean.setEname(employee.getEname());
			bean.setDesg(employee.getDesg());
			bean.setSalary(employee.getSalary());
			list.add(bean);
		}
		return list;

	}

	public List<EmployeeRequestBean> getEmployeeByEmpId(String empId) {
		List<Employee> listEmployee = dao.fetchEmloyeeByEmpId(empId);
		List<EmployeeRequestBean> employeeBean = new ArrayList<EmployeeRequestBean>();
		for (Iterator<Employee> iterator = listEmployee.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			EmployeeRequestBean bean = new EmployeeRequestBean();
			bean.setEname(employee.getEname());
			bean.setDesg(employee.getDesg());
			bean.setSalary(employee.getSalary());
			employeeBean.add(bean);
		}
		return employeeBean;
	}

}
