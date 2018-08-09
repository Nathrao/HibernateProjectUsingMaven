package com.nt.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.service.EmployeeService;

public class EmployeeControllerTestMockitoRule {

	
	@Mock
	public EmployeeService	   service;
	EmployeeController controller;
	
	@Rule
	public MockitoRule rule	= MockitoJUnit.rule();

	@Before
	public void setUp() {
		controller=new EmployeeController();
		controller.setService(service);
	}
	
	@Test
	public void testSaveEmployee() {
		EmployeeRequestBean employee = getMethodRequestBean(200, "Ram", "SE", 300);
		when(service.insertEmployee(employee)).thenReturn("Registration Successful");
		String result = controller.saveEmployee(employee);
		assertEquals("Registration Successful", result);
	}

	private EmployeeRequestBean getMethodRequestBean(int empId, String ename, String desig, int sal) {
		EmployeeRequestBean employee = new EmployeeRequestBean();
		employee.setEmpId(empId);
		employee.setEname(ename);
		employee.setDesg(desig);
		employee.setSalary(sal);
		return employee;
	}

}
