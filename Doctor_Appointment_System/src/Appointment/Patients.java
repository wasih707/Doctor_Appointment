package Appointment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Patients {

	public static void Patient_Menu() {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			displayMenu();
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (choice) {
			case 1:
				try {
					Insert_Patient();
				} catch (Exception e1) {
					System.out.println(e1);

				}

				break;
			case 2:
				try {
					Modify_Patient();
				} catch (Exception e1) {
					System.out.println(e1);

				}
				break;
			case 3:
				try {
					Delete_Patient();
				} catch (Exception e1) {
					System.out.println(e1);
				}

				break;
			case 4:
				try {
					PrintDetails();

				} catch (Exception e) {
					System.out.println(e);
				}

				break;
			case 5:
				try {
					PrintAllDetails();
				} catch (Exception e) {
					System.out.println(e);
				}

				break;
			case 0:
				System.out.println("Exiting the patient menu .");
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
				break;
			}
		} while (choice != 0);
	}

	private static void PrintDetails() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Patient ID");
		int p_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Patients where pat_id = " + p_id + ";");

		System.out.println("------PATIENT DETAILS--------");
		System.out.println("ID  Name  Age Gender Contact");


		while (rs.next()) {

			System.out.println(rs.getInt("pat_id") + " " + rs.getString("name") + " " + rs.getInt("age") + " "
					+ rs.getString("gender") + " " + rs.getString("contact"));

		}
		System.out.println("\n");
		System.out.println("---------------------------------");

		st.close();
		con.close();

	}

	public static void Modify_Patient() throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("\n--------------MODIFY PATIENT DETAILS-------------- \n");
		System.out.println("1. Change Name \n");
		System.out.println("2. Change Age \n");
		System.out.println("3. Change Gender \n");
		System.out.println("4. Change Contact Number \n");
		System.out.println("0. Exit\n");

		System.out.println("Enter your choice: ");
		int ch = sc.nextInt();
		int u_id;

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();

		switch (ch) {
		case 1:
			System.out.println("Enter Patient Id: ");
			u_id = sc.nextInt();
			System.out.println("Enter Patient name:- ");
			String u_name = sc.next();
			String ModNameQuery = " update patients set name = '" + u_name + "'where pat_id =" + u_id + ";";
			int res1 = st.executeUpdate(ModNameQuery);

			if (res1 == 0) {
				System.out.println("Patient not updated.\n Try again.");
			} else {
				System.out.println("Patient updated succesfully\n");
			}
			break;

		case 2:
			System.out.println("Enter Patient Id: ");
			u_id = sc.nextInt();
			System.out.println("Enter Patient's Age:- ");
			int u_age = sc.nextInt();
			String ModAgeQuery = " update patients set age = " + u_age + " where pat_id =" + u_id + ";";
			int res2 = st.executeUpdate(ModAgeQuery);

			if (res2 == 0) {
				System.out.println("Patient not updated\n Try again");
			} else {
				System.out.println("Patient updated successfully\n");
			}
			break;

		case 3:
			System.out.println("Enter Patient Id: ");
			u_id = sc.nextInt();
			System.out.println("Enter Patient's Gender:- ");
			String u_gen = sc.next();
			String ModGenQuery = " update patients set gender = '" + u_gen + "'where pat_id =" + u_id + ";";
			int res3 = st.executeUpdate(ModGenQuery);

			if (res3 == 0) {
				System.out.println("Patient not updated\n Try again");
			} else {
				System.out.println("Patient updated successfully\n");
			}
			break;

		case 4:
			System.out.println("Enter Patient Id: ");
			u_id = sc.nextInt();
			System.out.println("Enter Contact Number:- ");
			String u_num = sc.next();
			String ModNumQuery = " update patients set contact = '" + u_num + "'where pat_id =" + u_id + ";";
			int res4 = st.executeUpdate(ModNumQuery);

			if (res4 == 0) {
				System.out.println("Patient not updated\n Try again");
			} else {
				System.out.println("Patient updated successfully\n");
			}
			break;

		case 0:
			break;

		default:
			System.out.println("Invalid Choice. Enter a number from 0 to 4\n");
		}

		st.close();
		con.close();
		// sc.close();
	}



	private static void displayMenu() {
		System.out.println("\n You are in the Patients Menu. Enter an option");
		System.out.println(
				"\n\n1 - Register Patient\n\n2 - Modify Patient Details\n\n3 - Delete Patient Record\n\n4 - View a Patient\n\n"
						+ "5 - View All Patients\n\n0 - Exit\n");
		System.out.println("---------------------------------");

	}

	public static void PrintAllDetails() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Patients");
		System.out.println("\n------PATIENT DETAILS--------\n");

		System.out.println("ID  Name  Age Gender Contact");

		while (rs.next()) {

			System.out.println(
					rs.getInt("pat_id") + " " + rs.getString("name") + " " + rs.getInt("age") + " "
							+ rs.getString("gender") + " " + rs.getString("contact"));

		}
		System.out.println("\n");
		System.out.println("---------------------------------");

		st.close();
		con.close();
	}

	public static void Insert_Patient() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n------INSERT PATIENT--------\n");

		System.out.println("Enter Patient Name");
		String p_name = sc.next();
		System.out.println("Enter Patient Age");
		int p_age = sc.nextInt();
		System.out.println("Enter Patient Gender");
		String p_gen = sc.next();
		System.out.println("Enter Patient Contact Number");
		String p_num = sc.next();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		String insertQuery = "insert into Patients(name,age,gender,contact) " + "values('" + p_name + "'," + p_age
				+ ",'" + p_gen
				+ "'," + p_num + ")";
		int res = st.executeUpdate(insertQuery);

		if (res == 0) {
			System.out.println("\nPatient not inserted\n Try again");
		} else {
			System.out.println("\nPatient inserted successfully\n\n");
		}
		System.out.println("---------------------------------");
		st.close();
		con.close();

	}

	public static void Delete_Patient() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n------DELETE PATIENT--------\n");

		System.out.println("Enter Patient ID");
		int p_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		String deleteQuery = "delete from Patients where pat_id = " + p_id + ";";
		try {
		int res = st.executeUpdate(deleteQuery);

		if (res == 0) {
			System.out.println("\nPatient not deleted\n");
		} else {
			System.out.println("\nPatient deleted successfully\n");
		}
	} catch (Exception e) {
		System.out.println("\nYou can't delete a patient having an appointment. Delete the appointment first.\n");
	}
	System.out.println("---------------------------------");

		st.close();
		con.close();

	}

}
