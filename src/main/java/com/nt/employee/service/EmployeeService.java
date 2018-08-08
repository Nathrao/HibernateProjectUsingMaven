package com.nt.employee.service;

import java.util.List;

import com.nt.controller.beans.EmployeeRequestBean;

public interface EmployeeService {
	public String insertEmployee(EmployeeRequestBean employee);
	public List<EmployeeRequestBean> getAllEmployees();
	public List<EmployeeRequestBean> getEmployeeByEmpId(String empId);
}
