package com.nt.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.nt.controller.EmployeeController;
import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.service.EmployeeService;

public class EmployeeControllerTest {

	private EmployeeController controller;
	private EmployeeService service;

	@Before
	public void setUp() throws Exception {
		controller = new EmployeeController();
		service = Mockito.mock(EmployeeService.class);
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

	@Test
	public void testSaveEmployee_RequestNull() {

		String result = controller.saveEmployee(null);
		assertEquals("Request Object Null", result);
	}

	@Test
	public void testSaveEmployee_EmployeeIdZero() {

		EmployeeRequestBean employee = getMethodRequestBean(0, "Ram", "SE", 300);
		String result = controller.saveEmployee(employee);
		assertEquals("EmpId is not Valid", result);
	}

	@Test
	public void testSaveEmployee_EmployeeNameBlank() {

		EmployeeRequestBean employee = getMethodRequestBean(4, "", "SE", 300);
		String result = controller.saveEmployee(employee);
		assertEquals("EmpName is not valid", result);
	}

	@Test
	public void testSaveEmployee_EmployeeSalaryZero() {
		EmployeeRequestBean employee = getMethodRequestBean(3, "Ram", "SE", 0);
		String result = controller.saveEmployee(employee);
		assertEquals("Invalid salary amount", result);
	}

	@Test
	public void testSaveEmployee_EmployeeDesignation() {

		EmployeeRequestBean employee = getMethodRequestBean(3, "Ram", "", 67);
		String result = controller.saveEmployee(employee);
		assertEquals("Invalid Designation", result);
	}

	@Test
	public void testSaveEmployee_EmployeeDesignationNul() {

		EmployeeRequestBean employee = getMethodRequestBean(3, "Ram", null, 89);
		String result = controller.saveEmployee(employee);
		assertEquals("Invalid Designation", result);
	}
	
	@Test
	public void  testDisplayAllEmployees_NullValues()
	{	
		List<EmployeeRequestBean> list=new ArrayList<EmployeeRequestBean>();
		list.add(getMethodRequestBean(123, "Ram", "SE", 89));	
		when(service.getAllEmployees()).thenReturn(list);
			
		List<EmployeeRequestBean>  result=controller.displayAllEmployees();
        assertNotNull(result);
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			EmployeeRequestBean employeeRequestBean = (EmployeeRequestBean) iterator.next();
			
			 assertEquals(123, employeeRequestBean.getEmpId());
			 assertEquals("Ram", employeeRequestBean.getEname());
			 assertEquals("SE", employeeRequestBean.getDesg());
			 assertEquals(89, employeeRequestBean.getSalary());
		}
       
	}
	
	@Test
	public void testdisplayEmployeeByEmpId()
	{
		List<EmployeeRequestBean> list=new ArrayList<EmployeeRequestBean>();
		when(service.getEmployeeByEmpId("123")).thenReturn(list);
		List<EmployeeRequestBean> result=controller.displayEmployeeByEmpId("123");
		assertNotNull(result);
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			EmployeeRequestBean employeeRequestBean = (EmployeeRequestBean) iterator.next();
			
			assertEquals(123, employeeRequestBean.getEmpId());
			assertEquals("Ram", employeeRequestBean.getEname());
			assertEquals("SE", employeeRequestBean.getDesg());
			assertEquals(89, employeeRequestBean.getSalary());
		}
	}
	
	@Test
	public void testdisplayEmployeeByEmpId_Null()
	{
		when(service.getEmployeeByEmpId(null)).thenReturn(null);
		List<EmployeeRequestBean> result=controller.displayEmployeeByEmpId(null);
		assertNull(result);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
	}
}
