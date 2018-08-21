package com.jr.employee.service;

import java.util.List;

import com.jr.controller.beans.EmployeeRequestBean;
import com.jr.controller.beans.EmployeeResponseBean;

public interface EmployeeService {
	public String insertEmployee(EmployeeRequestBean employee);

	public List<EmployeeResponseBean> getAllEmployees();

	public List<EmployeeResponseBean> getEmployeeByEmpId(String empId);
}
