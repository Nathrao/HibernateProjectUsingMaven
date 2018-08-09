package com.nt.controller;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.controller.beans.EmployeeResponseBean;
import com.nt.employee.service.EmployeeService;

public class EmpControllerTest {

	private EmployeeController controller;
	private EmployeeService	   service;

	@Before
	public void setUp() throws Exception {
		controller = new EmployeeController();
		service = EasyMock.createMock(EmployeeService.class);
		controller.setService(service);
	}

	@Test
	public void testSaveEmployee() {
		String msg = "Registration Successful";
		EasyMock.expect(service.insertEmployee(EasyMock.isA(EmployeeRequestBean.class))).andReturn(msg);
		EasyMock.replay(service);
		EmployeeRequestBean employee = getMethodRequestBean(200, "Ram", "SE", 300);
		String result = controller.saveEmployee(employee);
		Assert.assertEquals("Registration Successful", result);
	}

	@Test
	public void testDisplayEmployeeByEmpId() {
		List<EmployeeResponseBean> employeeRequestBeans = new ArrayList<EmployeeResponseBean>();
		EasyMock.expect(service.getEmployeeByEmpId(EasyMock.isA(String.class))).andReturn(employeeRequestBeans);
		EasyMock.replay(service);
		List<EmployeeResponseBean> list = controller.displayEmployeeByEmpId("123");
		Assert.assertNotNull(list);
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
