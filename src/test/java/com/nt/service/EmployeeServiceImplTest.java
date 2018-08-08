package com.nt.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.dao.service.EmployeeDAO;
import com.nt.employee.service.impl.EmployeeServiceImpl;
import com.nt.entity.Employee;

public class EmployeeServiceImplTest {

	private EmployeeServiceImpl	service;
	private EmployeeDAO			dao;

	@Before
	public void setUp() {
		service = new EmployeeServiceImpl();
		dao = Mockito.mock(EmployeeDAO.class);
		service.setDao(dao);

	}

	@Test
	public void testInsertEmployee_WithNull() {
		String result = service.insertEmployee(null);
		assertEquals("Null object received", result);
	}

	@Test
	public void testInsertEmployee() {
		EmployeeRequestBean employee = new EmployeeRequestBean();
		employee.setEmpId(123);
		employee.setEname("nath");
		employee.setDesg("SE");
		employee.setSalary(2000);
		Employee bean = new Employee();
		bean.setEmpId(123);
		bean.setEname("nath");
		bean.setDesg("SE");
		bean.setSalary(2000);
		when(dao.saveEmployee(bean)).thenReturn("Registration Successful");
		String result = service.insertEmployee(employee);
		assertEquals("Registration Successful", result);
	}

	@After
	public void teatDown() {
		service = null;
		dao = null;
	}

}
