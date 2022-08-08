package com.shroidenger;

//Properties file
//common class for connection
//rename Employee to EmployeeDTO
//Services toUpperCase

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {

		EmployeeDAO.DatabaseCreator();

		boolean running = true;
		Scanner scanner = new Scanner(System.in);

		headerfooter();

		while (running) {
			System.out.println("1. ADD EMPLOYEES");
			System.out.println("2. VIEW EMPLOYEES");
			System.out.println("3. DELETE EMPLOYEES");
			System.out.println("4. UPDATE EMPLOYEES");
			System.out.println("5. VIEW ALL EMPLOYEES");
			System.out.println("6. VIEW ALL DELETED EMPLOYEES");
			System.out.println("7. EXIT");
			System.out.println("ENTER SELECTION: ");

			int choice = scanner.nextInt();

			switch (choice) {

			case 1: {

				System.out.println();
				System.out.println("«»«»«»«» ADD «»«»«»«»");
				System.out.println();

				System.out.println("ID: ");
				int empid = scanner.nextInt();

				if (EmployeeDAO.IDChecker(empid)) {

					System.out.println();
					System.out.println("ID EXISTS");
					System.out.println();
				} else {

					System.out.println("ENTER NAME: ");
					String empname = scanner.next();

					System.out.println("ENTER CITY: ");
					String empcity = scanner.next();

					Employee employee = new Employee(empid, empname, empcity, "Yes");

					// ADD CONFIRMATION

					EmployeeDAO.EmpAdder(employee);
					System.out.println();
					System.out.println("=================================================");
					System.out.println();

				}

				break;
			}
			case 2: {

				System.out.println();
				System.out.println("«»«»«»«» VIEW «»«»«»«»");
				System.out.println();
				System.out.println("ENTER ID: ");

				if (!EmployeeDAO.IsEmpty()) {
					int viewval = scanner.nextInt();
					if (EmployeeDAO.IDChecker(viewval)) {

						System.out.println(EmployeeDAO.EmpView(viewval));

					} else {
						System.out.println("INVALID ID");
					}
					System.out.println();
					System.out.println("=================================================");
					System.out.println();
					break;
				} else {
					System.out.println("NO VALUES");
					break;
				}
			}
			case 3: {

				System.out.println();
				System.out.println("«»«»«»«» DELETE «»«»«»«»");
				System.out.println();
				System.out.println("ENTER ID: ");
				int delval = scanner.nextInt();
				if (EmployeeDAO.IDChecker(delval)) {

					EmployeeDAO.EmpDelete(delval);

				} else {
					System.out.println("INVALID ID");
				}
				System.out.println();
				System.out.println("=================================================");
				System.out.println();
				break;
			}
			case 4: {

				System.out.println();
				System.out.println("«»«»«»«» UPDATE «»«»«»«»");
				System.out.println();
				System.out.println("ENTER ID: ");
				int updateval = scanner.nextInt();

				if (EmployeeDAO.IDChecker(updateval)) {
					EmployeeDAO.EmpUpdate(updateval);
				} else {

					System.out.println("INVALID ID");
				}

				break;
			}
			case 5: {

				System.out.println();
				System.out.println("«»«»«»«» VIEWALL «»«»«»«»");
				System.out.println();
				if (EmployeeDAO.IsEmpty()) {
					System.out.println("EMPTY");
				} else {

					EmployeeDAO.ViewAllEmp();
				}
				System.out.println();
				System.out.println("=================================================");
				System.out.println();
				break;
			}
			case 6: {
				System.out.println();
				System.out.println("«»«»«»«» VIEWALLDELETEDEMP «»«»«»«»");
				System.out.println();
				if (EmployeeDAO.IsEmpty()) {
					System.out.println("EMPTY");
				} else {
					EmployeeDAO.ViewDeletedEmp();
				}
				System.out.println();
				System.out.println("=================================================");
				System.out.println();
				break;
			}

			case 7: {
				running = false;
				System.out.println();
				System.out.println("«»«»«»«» EXITING «»«»«»«»");
				System.out.println();
				headerfooter();
				System.exit(0);
				break;
			}
			default: {
				System.out.println("WRONG");
				break;
			}
			}

		}

	}

	private static void headerfooter() {

		for (int i = 0; i < 30; i++) {

			System.out.print("«»");

		}
		System.out.println();

	}

}