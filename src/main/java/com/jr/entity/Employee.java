package com.jr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	public int	  empId;
	@Column(length = 30)
	public String ename;
	@Column(length = 10)
	public String desg;
	@Column(length = 30)
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

}
