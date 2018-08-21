package com.jr.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jr.controller.beans.EmployeeResponseBean;
import com.jr.employee.service.EmployeeService;

public class EmpControllerMockMVC {

	private MockMvc mockMvc;

	@InjectMocks
	private EmployeeController controller;
	@Mock
	private EmployeeService	   service;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void testDisplayAllEmployees() throws Exception {
		List<EmployeeResponseBean> responseBeans = new ArrayList<EmployeeResponseBean>();
		Mockito.when(controller.displayAllEmployees()).thenReturn(responseBeans);
		// mockMvc.perform(MockMvcRequestBuilders.get("/employeeDetails")).andExpect(MockMvcResultMatchers.status().isOk())
		// .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
		Mockito.verify(controller, Mockito.times(1)).displayAllEmployees();
	}

}
