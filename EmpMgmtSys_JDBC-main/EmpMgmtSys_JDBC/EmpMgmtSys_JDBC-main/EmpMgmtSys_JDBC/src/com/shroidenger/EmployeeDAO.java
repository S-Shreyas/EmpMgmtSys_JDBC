package com.shroidenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeDAO {
//	public static Boolean checker(int val, ArrayList<Employee> arr) {
//
//		for (Employee counter : arr) {
//
//			if (counter.getId() == val) {
//				return true;
//			}
//
//		}
//		return false;
//
//	}

	public static Boolean IDChecker(int val) throws SQLException {

		ArrayList<Integer> arr = new ArrayList<>();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT uid FROM empinfo");

		while (rs.next()) {

			int toint = Integer.parseInt(rs.getString("uid"));
			arr.add(toint);

		}

		for (int i : arr) {

			if (i == val) {
				return true;
			}

		}
		return false;

	}

	public static void DatabaseCreator() throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		Statement stmt = con.createStatement();

		stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS EmpSys");
		stmt.executeUpdate("USE EmpSys");
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS empinfo(uid int, name varchar(20), city varchar(20), empstatus ENUM(\"Yes\", \"No\"), PRIMARY KEY (uid))");

	}

	public static void EmpAdder(Employee employee) throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement(); // preparedstatement (?), (?) sql injection

		int empid = employee.getId();
		String empname = employee.getName();
		String empcity = employee.getCity();
		String sqlQuery = String.format("INSERT INTO empinfo values(%d, \"%s\", \"%s\", \"%s\")", empid, empname,
				empcity, "Yes");

		stmt.executeUpdate(sqlQuery);
	}

	public static Employee EmpView(int val) throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement();
		String sqlQuery = String.format("SELECT * FROM empinfo WHERE uid=%d", val);

		ResultSet rs = stmt.executeQuery(sqlQuery);
		Employee employee = new Employee();

		while (rs.next()) {

			employee.setId(rs.getInt("uid"));
			employee.setName(rs.getString("name"));
			employee.setCity(rs.getString("city"));
			employee.setEmpstatus(rs.getString("empstatus"));

		}
		return employee;

	}

	public static void EmpDelete(int val) throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement();

		String sqlQuery = String.format("update empinfo set empstatus=\"No\" where uid=%d", val);

		stmt.executeUpdate(sqlQuery);
	}

	public static void EmpUpdate(int val) throws SQLException {

		Employee employee = EmployeeDAO.EmpView(val);

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement();

		Scanner scanner = new Scanner(System.in);

		System.out.println("ENTER NAME (LEAVE BLANK IF UNCHANGED): ");
		String upname = scanner.nextLine();
		if (upname.equals("")) {
			upname = employee.getName();

		}

		System.out.println("ENTER CITY (LEAVE BLANK IF UNCHANGED): ");
		String upcity = scanner.nextLine();
		if (upcity.equals("")) {
			upcity = employee.getCity();

		}

		System.out.println("ENTER STATUS (LEAVE BLANK IF UNCHANGED): ");
		String upstatus = scanner.nextLine();

		if (upstatus.equals("")) {
			upstatus = employee.getEmpstatus();

		}

		String sqlQuery = String.format(
				"UPDATE empinfo SET name=\"%s\", city=\"%s\", empstatus=\"%s\" " + "WHERE uid=%d", upname, upcity,
				upstatus, val);

		stmt.executeUpdate(sqlQuery);
	}

	public static void ViewAllEmp() throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement();
		String sqlQuery = String.format("SELECT * FROM empinfo WHERE empstatus=\"Yes\"");

		ResultSet rs = stmt.executeQuery(sqlQuery);
		Employee employee = new Employee();

		while (rs.next()) {

			employee.setId(rs.getInt("uid"));
			employee.setName(rs.getString("name"));
			employee.setCity(rs.getString("city"));
			employee.setEmpstatus(rs.getString("empstatus"));
			System.out.println(employee);

		}

	}

	public static void ViewDeletedEmp() throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement();
		String sqlQuery = String.format("SELECT * FROM empinfo WHERE empstatus=\"No\"");

		ResultSet rs = stmt.executeQuery(sqlQuery);
		Employee employee = new Employee();

		while (rs.next()) {

			employee.setId(rs.getInt("uid"));
			employee.setName(rs.getString("name"));
			employee.setCity(rs.getString("city"));
			employee.setEmpstatus(rs.getString("empstatus"));
			System.out.println(employee);

		}

	}

	public static Boolean IsEmpty() throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpSys", "root", "");
		Statement stmt = con.createStatement();
		String sqlQuery = String.format("SELECT * FROM empinfo LIMIT 1");

		ResultSet rs = stmt.executeQuery(sqlQuery);
		int count = 0;

		while (rs.next()) {
			count++;
		}
		if (count == 0) {
			return true;
		}

		return false;

	}

}