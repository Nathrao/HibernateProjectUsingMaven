package com.jr.employee.dao.service;

import java.util.List;

import com.jr.entity.Employee;

public interface EmployeeDAO {
	public String saveEmployee(Employee employee);

	public List<Employee> fetchAllEmployees();

	public List<Employee> fetchEmloyeeByEmpId(String empId);

}
