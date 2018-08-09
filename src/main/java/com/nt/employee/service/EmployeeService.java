package com.nt.employee.service;

import java.util.List;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.controller.beans.EmployeeResponseBean;

public interface EmployeeService {
	public String insertEmployee(EmployeeRequestBean employee);

	public List<EmployeeResponseBean> getAllEmployees();

	public List<EmployeeResponseBean> getEmployeeByEmpId(String empId);
}
