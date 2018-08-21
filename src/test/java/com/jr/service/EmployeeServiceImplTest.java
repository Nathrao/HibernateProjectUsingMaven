package com.jr.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.jr.controller.beans.EmployeeRequestBean;
import com.jr.employee.dao.service.EmployeeDAO;
import com.jr.employee.service.impl.EmployeeServiceImpl;
import com.jr.entity.Employee;

@RunWith(PowerMockRunner.class)
@PrepareForTest
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
		Assert.assertEquals("Null object received", result);
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
		Mockito.when(dao.saveEmployee(bean)).thenReturn("Registration Successful");
		String result = service.insertEmployee(employee);
		Assert.assertEquals("Registration Successful", result);
	}

	@After
	public void teatDown() {
		service = null;
		dao = null;
	}

}
