package com.nt.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nt.controller.beans.EmployeeRequestBean;
import com.nt.employee.service.EmployeeService;

@Controller
@RequestMapping("/employeeDetails")
public class EmployeeController {

	final static Logger		log	= Logger.getLogger(EmployeeController.class);
	@Autowired
	@Qualifier(value = "EmployeeService")
	private EmployeeService	service;

	public EmployeeService getService() {
		return service;
	}

	public void setService(EmployeeService service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "EmployeeController [service=" + service + "]";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String saveEmployee(@RequestBody EmployeeRequestBean employee) {
		log.trace("EmployeeController.saveEmployee() entry");
		if (employee != null) {
			log.debug("Requst received"+employee);
			if (employee.getEmpId() <= 0) {
				log.debug("invalid value " + employee.getEmpId());
				return "EmpId is not Valid";
			}
			else if (employee.getEname() == null || employee.getEname().isEmpty() == true) {
				log.debug("invalid value " + employee.getEname());
				return "EmpName is not valid";
			}
			else if (employee.getSalary() <= 0) {
				log.debug("invalid value " + employee.getSalary());
				return "Invalid salary amount";
			}
			else if (employee.getDesg() == null || employee.getDesg().isEmpty() == true) {
				log.debug("invalid value " + employee.getDesg());
				return "Invalid Designation";
			}
		}
		else {
			return "Request Object Null";
		}
		log.trace("EmployeeController.saveEmployee() exit");
		return service.insertEmployee(employee);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeRequestBean> displayAllEmployees() {

		List<EmployeeRequestBean> listEmployee = service.getAllEmployees();
		return listEmployee;
	}

	@RequestMapping(value = "/listEmp/{empId}", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeRequestBean> displayEmployeeByEmpId(@PathVariable String empId) {
		List<EmployeeRequestBean> list = service.getEmployeeByEmpId(empId);
		return list;
	}

}
