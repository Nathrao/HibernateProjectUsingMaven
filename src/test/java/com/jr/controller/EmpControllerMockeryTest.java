package com.jr.controller;

import org.jmock.AbstractExpectations;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jr.controller.beans.EmployeeRequestBean;
import com.jr.employee.service.EmployeeService;

public class EmpControllerMockeryTest {

	private EmployeeController controller;
	private EmployeeService	   service;

	@Before
	public void setUp() throws Exception {
		controller = new EmployeeController();
		service = context.mock(EmployeeService.class);
		controller.setService(service);
	}

	public Mockery context = new JUnit4Mockery();

	@Test
	public void testSaveEmployee() {
		final EmployeeRequestBean employee = getMethodRequestBean(200, "Ram", "SE", 300);
		context.checking(new Expectations() {
			{
				atLeast(1).of(service).insertEmployee(employee);
				will(AbstractExpectations.returnValue("Registration Successful"));
			}
		});

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
