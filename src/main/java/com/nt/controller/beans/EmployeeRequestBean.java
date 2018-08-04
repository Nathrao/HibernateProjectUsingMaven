package com.nt.controller.beans;

public class EmployeeRequestBean {
	
	public int empId;
	public String ename;
	public String desg;
	public int salary;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeRequestBean [empId=" + empId + ", ename=" + ename + ", desg=" + desg + ", salary=" + salary
				+ "]";
	}

}
