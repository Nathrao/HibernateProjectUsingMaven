package com.jr.controller.beans;

public class EmployeeResponseBean {
	public int	  empId;
	public String ename;
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
		return "EmployeeResponseBean [empId=" + empId + ", ename=" + ename + ", desg=" + desg + ", salary=" + salary
				+ "]";
	}

}
