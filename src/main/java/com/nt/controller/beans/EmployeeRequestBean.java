package com.nt.controller.beans;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class EmployeeRequestBean {
	public int	  empId;
	@NotNull(message = "name is null")
	public String ename;
	@NotNull(message = "designation is null")
	public String desg;
	public int	  salary;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(final int empId) {
		this.empId = empId;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(final String ename) {
		this.ename = ename;
	}

	public String getDesg() {
		return desg;
	}

	public void setDesg(final String desg) {
		this.desg = desg;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(final int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeRequestBean [empId=" + empId + ", ename=" + ename + ", desg=" + desg + ", salary=" + salary
				+ "]";
	}

}
