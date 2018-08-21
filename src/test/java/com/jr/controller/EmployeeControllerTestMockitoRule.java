package com.jr.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.jr.controller.beans.EmployeeRequestBean;
import com.jr.employee.service.EmployeeService;

@RunWith(PowerMockRunner.class)
@PrepareForTest
public class EmployeeControllerTestMockitoRule {

	@Mock
	public EmployeeService service;
	EmployeeController	   controller;

	/*
	 * @Rule public MockitoRule rule = MockitoJUnit.rule();
	 */
	@Before
	public void setUp() {
		controller = new EmployeeController();
		controller.setService(service);
	}

	@Test
	public void testSaveEmployee() {
		EmployeeRequestBean employee = getMethodRequestBean(200, "Ram", "SE", 300);
		PowerMockito.when(service.insertEmployee(employee)).thenReturn("Registration Successful");
		String result = controller.saveEmployee(employee);
		Assert.assertEquals("Registration Successful", result);
	}

	private EmployeeRequestBean getMethodRequestBean(final int empId, final String ename, final String desig,
			final int sal) {
		EmployeeRequestBean employee = new EmployeeRequestBean();
		employee.setEmpId(empId);
		employee.setEname(ename);
		employee.setDesg(desig);
		employee.setSalary(sal);
		return employee;
	}
}
