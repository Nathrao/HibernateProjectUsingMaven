package com.nt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nt.employee.dao.service.impl.EmployeeDAOImpl;
import com.nt.entity.Employee;

public class EmployeeDAOImplTest {

	private EmployeeDAOImpl	dao;
	private SessionFactory	factory;
	private Session			session;
	private Transaction		tx;
	private Criteria		crit;
	private List			list;
	private Criterion		criterion;

	@Before
	public void setcUp() {

		dao = new EmployeeDAOImpl();
		session = EasyMock.createMock(Session.class);
		tx = EasyMock.createMock(Transaction.class);
		factory = EasyMock.createMock(SessionFactory.class);
		crit = EasyMock.createMock(Criteria.class);
		list = EasyMock.createMock(List.class);
		criterion = EasyMock.createMock(Criterion.class);
		dao.setFactory(factory);
	}

	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee();
		employee.setEmpId(12);
		employee.setEname("NAth");
		employee.setDesg("SE");
		employee.setSalary(1234);

		EasyMock.expect(factory.openSession()).andReturn(session);
		EasyMock.replay(factory);
		EasyMock.expect(session.beginTransaction()).andReturn(tx);
		EasyMock.expect(session.save(EasyMock.isA(Employee.class))).andReturn("67");
		EasyMock.replay(session);
		String result = dao.saveEmployee(employee);
		assertEquals("Registration Successful", result);
	}

	@Test
	public void testFetchAllEmployees() {
		List<Employee> bean = new ArrayList<Employee>();
		EasyMock.expect(factory.openSession()).andReturn(session);
		EasyMock.replay(factory);
		EasyMock.expect(session.createCriteria(Employee.class)).andReturn(crit);
		EasyMock.replay(session);
		EasyMock.expect(crit.list()).andReturn(bean);
		EasyMock.replay(crit);
		List<Employee> list = dao.fetchAllEmployees();
		assertNotNull(list);
	}

	@Test
	public void testFetchEmloyeeByEmpId() {
		List<Employee> bean = new ArrayList<Employee>();
		EasyMock.expect(factory.openSession()).andReturn(session);
		EasyMock.replay(factory);
		EasyMock.expect(session.createCriteria(Employee.class)).andReturn(crit);
		EasyMock.replay(session);
		EasyMock.expect(crit.add(EasyMock.isA(Criterion.class))).andReturn(crit);
		EasyMock.expect(crit.list()).andReturn(bean);
		EasyMock.replay(crit);
		List<Employee> list = dao.fetchEmloyeeByEmpId("123");
		assertNotNull(list);
	}

	@After
	public void tearDown() {
		dao = null;
	}

}
