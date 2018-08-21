package com.jr.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jr.controller.beans.EmployeeRequestBean;
import com.jr.controller.beans.EmployeeResponseBean;
import com.jr.employee.service.EmployeeService;

public class EmployeeControllerTest {

	private EmployeeController controller;
	private EmployeeService	   service;

	@Before
	public void setUp() throws Exception {
		controller = new EmployeeController();
		service = Mockito.mock(EmployeeService.class);
		controller.setService(service);
	}

	@Test
	public void testSaveEmployee() {

		EmployeeRequestBean employee = getMethodRequestBean(200, "Ram", "SE", 300);
		Mockito.when(service.insertEmployee(employee)).thenReturn("Registration Successful");
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

	private EmployeeResponseBean getResponseBean(final int empId, final String ename, final String desig,
			final int sal) {
		EmployeeResponseBean employee = new EmployeeResponseBean();
		employee.setEmpId(empId);
		employee.setEname(ename);
		employee.setDesg(desig);
		employee.setSalary(sal);
		return employee;
	}

	@Test
	public void testSaveEmployee_RequestNull() {

		String result = controller.saveEmployee(null);
		Assert.assertEquals("Request Object Null", result);
	}

	@Test
	public void testSaveEmployee_EmployeeIdZero() {

		EmployeeRequestBean employee = getMethodRequestBean(0, "Ram", "SE", 300);
		String result = controller.saveEmployee(employee);
		Assert.assertEquals("EmpId is not Valid", result);
	}

	@Test
	public void testSaveEmployee_EmployeeNameBlank() {

		EmployeeRequestBean employee = getMethodRequestBean(4, "", "SE", 300);
		String result = controller.saveEmployee(employee);
		Assert.assertEquals("EmpName is not valid", result);
	}

	@Test
	public void testSaveEmployee_EmployeeSalaryZero() {
		EmployeeRequestBean employee = getMethodRequestBean(3, "Ram", "SE", 0);
		String result = controller.saveEmployee(employee);
		Assert.assertEquals("Invalid salary amount", result);
	}

	@Test
	public void testSaveEmployee_EmployeeDesignation() {

		EmployeeRequestBean employee = getMethodRequestBean(3, "Ram", "", 67);
		String result = controller.saveEmployee(employee);
		Assert.assertEquals("Invalid Designation", result);
	}

	@Test
	public void testSaveEmployee_EmployeeDesignationNul() {

		EmployeeRequestBean employee = getMethodRequestBean(3, "Ram", null, 89);
		String result = controller.saveEmployee(employee);
		Assert.assertEquals("Invalid Designation", result);
	}

	@Test
	public void testDisplayAllEmployees_NullValues() {
		List<EmployeeResponseBean> list = new ArrayList<EmployeeResponseBean>();
		list.add(getResponseBean(123, "Ram", "SE", 89));
		Mockito.when(service.getAllEmployees()).thenReturn(list);

		List<EmployeeResponseBean> result = controller.displayAllEmployees();
		Assert.assertNotNull(result);
		for (Iterator<EmployeeResponseBean> iterator = result.iterator(); iterator.hasNext();) {
			EmployeeResponseBean employeeRequestBean = iterator.next();

			Assert.assertEquals(123, employeeRequestBean.getEmpId());
			Assert.assertEquals("Ram", employeeRequestBean.getEname());
			Assert.assertEquals("SE", employeeRequestBean.getDesg());
			Assert.assertEquals(89, employeeRequestBean.getSalary());
		}

	}

	@Test
	public void testdisplayEmployeeByEmpId() {
		List<EmployeeResponseBean> list = new ArrayList<EmployeeResponseBean>();
		Mockito.when(service.getEmployeeByEmpId("123")).thenReturn(list);
		List<EmployeeResponseBean> result = controller.displayEmployeeByEmpId("123");
		Assert.assertNotNull(result);
		for (Iterator<EmployeeResponseBean> iterator = result.iterator(); iterator.hasNext();) {
			EmployeeResponseBean employeeRequestBean = iterator.next();

			Assert.assertEquals(123, employeeRequestBean.getEmpId());
			Assert.assertEquals("Ram", employeeRequestBean.getEname());
			Assert.assertEquals("SE", employeeRequestBean.getDesg());
			Assert.assertEquals(89, employeeRequestBean.getSalary());
		}
	}

	@Test
	public void testdisplayEmployeeByEmpId_Null() {
		Mockito.when(service.getEmployeeByEmpId(null)).thenReturn(null);
		List<EmployeeResponseBean> result = controller.displayEmployeeByEmpId(null);
		Assert.assertNull(result);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
	}
}