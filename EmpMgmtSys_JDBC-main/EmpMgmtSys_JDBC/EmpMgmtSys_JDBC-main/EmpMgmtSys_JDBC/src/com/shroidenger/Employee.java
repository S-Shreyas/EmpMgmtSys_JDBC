package com.shroidenger;

public class Employee {

	private int id;
	private String name;
	private String city;
	private String empstatus;

	public Employee() {

	}

	public Employee(int id, String name, String city, String empstatus) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.empstatus = empstatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmpstatus() {
		return empstatus;
	}

	public void setEmpstatus(String empstatus) {
		this.empstatus = empstatus;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", city=" + city + ", empstatus=" + empstatus + "]";
	}

}