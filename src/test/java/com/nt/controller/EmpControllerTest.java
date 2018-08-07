package com.nt.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.service.EmployeeService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EmployeeController.class })
public class EmpControllerTest {

	EmployeeController	controller	= null;
	@Mock	EmployeeService		service		= null;


	

	@Test
	public void testSaveEmployee() {
		EmployeeRequestBean employee = getMethodRequestBean(200, "Ram", "SE", 300);
		expect(EmployeeService.class).andReturn(service);
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
