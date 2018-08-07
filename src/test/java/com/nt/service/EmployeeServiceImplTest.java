package com.nt.service;

import static org.junit.Assert.assertNull;

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
	public void testInsertEmployee()
	{
		String result=service.insertEmployee(null);
		assertNull(result);
	}
	
	public void teatDown() {
		service = null;
		dao = null;
	}

}
