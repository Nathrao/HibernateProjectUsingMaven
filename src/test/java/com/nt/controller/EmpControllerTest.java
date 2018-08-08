package com.nt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.service.EmployeeService;

public class EmpControllerTest {

	private EmployeeController	controller;
	private EmployeeService		service;

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
		assertEquals("Registration Successful", result);
	}

	@Test
	public void testDisplayEmployeeByEmpId() {
		List<EmployeeRequestBean> employeeRequestBeans = new ArrayList<EmployeeRequestBean>();
		EasyMock.expect(service.getEmployeeByEmpId(EasyMock.isA(String.class))).andReturn(employeeRequestBeans);
		EasyMock.replay(service);
		List<EmployeeRequestBean> list = controller.displayEmployeeByEmpId("123");
		assertNotNull(list);
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
