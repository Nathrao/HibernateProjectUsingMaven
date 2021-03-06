package com.jr.service;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.jr.controller.beans.EmployeeRequestBean;
import com.jr.controller.beans.EmployeeResponseBean;
import com.jr.employee.dao.service.EmployeeDAO;
import com.jr.employee.service.impl.EmployeeServiceImpl;
import com.jr.entity.Employee;

@RunWith(PowerMockRunner.class)
@PrepareForTest
public class EmpServiceImplTest {

	private EmployeeServiceImpl	service;
	private EmployeeDAO			dao;

	@Before
	public void setUp() {
		service = new EmployeeServiceImpl();
		dao = EasyMock.createMock(EmployeeDAO.class);
		service.setDao(dao);
	}

	@Test
	public void testInsertEmployee() {
		String msg = "Registration Successful";
		EasyMock.expect(dao.saveEmployee(EasyMock.isA(Employee.class))).andReturn(msg);
		EasyMock.replay(dao);
		EmployeeRequestBean employee = new EmployeeRequestBean();
		employee.setEmpId(123);
		employee.setEname("Nath");
		employee.setDesg("SE");
		employee.setSalary(2000);
		String result = service.insertEmployee(employee);
		Assert.assertEquals("Registration Successful", result);
	}

	@Test
	public void testgetAllEmployees() {
		List<Employee> bean = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setEmpId(123);
		emp.setEname("Nath");
		emp.setDesg("SE");
		emp.setSalary(2000);
		bean.add(emp);
		EasyMock.expect(dao.fetchAllEmployees()).andReturn(bean);
		EasyMock.replay(dao);
		List<EmployeeResponseBean> list = service.getAllEmployees();

		Assert.assertNotNull(list);
		EmployeeResponseBean empBean = list.get(0);
		Assert.assertEquals(123, empBean.getEmpId());
		Assert.assertEquals("Nath", empBean.getEname());
		Assert.assertEquals("SE", empBean.getDesg());
		Assert.assertEquals(2000, empBean.getSalary());

	}

	@After
	public void tearDown() {

		service = null;
		dao = null;

	}
}
