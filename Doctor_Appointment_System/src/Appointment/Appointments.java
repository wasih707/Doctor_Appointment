package Appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Appointments {
	public static void Appointment_Menu() {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			displayMenu();
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (choice) {
			case 1:
				try {
					Insert_Appointment();
				} catch (Exception e1) {
					System.out.println(e1);

				}

				break;
			case 2:
				try {
					Modify_Appointment();
				} catch (Exception e1) {
					System.out.println(e1);

				}
				break;
			case 3:
				try {
					Delete_Appointmentt();
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
				System.out.println("Exiting the appointment menu .");
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
				break;
			}
		} while (choice != 0);
	}

	private static void PrintAllDetails() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Appointments");
		System.out.println("------APPOINTMENT DETAILS---------");

		System.out.println("App_ID  Doc_ID  Pat_ID Date Time");

		while (rs.next()) {

			System.out.println(rs.getInt("app_id") + " " + rs.getInt("doc_id") + " " + rs.getInt("pat_id") + " "
					+ rs.getString("app_date") + " " + rs.getString("app_time") + " " + rs.getString("reason"));

		}
		System.out.println("\n");
		System.out.println("---------------------------------");

		st.close();
		con.close();
	}



	private static void PrintDetails() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Appointment ID");
		int a_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Appointments where app_id = " + a_id + ";");
		System.out.println("-------APPOINTMENT DETAILS--------");

		System.out.println("App_ID | Doc_ID | Pat_ID | Date | Time | Reason");

		while (rs.next()) {

			System.out.println(rs.getInt("app_id") + " " + rs.getInt("doc_id") + " " + rs.getInt("pat_id") + " "
					+ rs.getString("app_date") + " " + rs.getString("app_time") + " " + rs.getString("reason"));

		}
		System.out.println("\n");
		System.out.println("---------------------------------");

		st.close();
		con.close();

	}

	private static void Delete_Appointmentt() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------DELETE APPOINTMENT-------");

		System.out.println("Enter Appointment ID");
		int a_id = sc.nextInt();

		System.out.println("Enter appointment ID again");
		int a2_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		String deleteQuery = "delete from Appointments where app_id = " + a_id + ";";
		try {
			if (a_id == a2_id) {
				int res = st.executeUpdate(deleteQuery);

		if (res == 0) {
					System.out.println("\nAppointment not deleted\n");
		} else {
					System.out.println("\nAppointment deleted successfully\n");
		}
			} else {
				System.out.println("\nIDs don't match\n");
			}
		} catch (Exception e) {
			System.out.println("\nDeletio0n failed. Try again.\n");
		}
		System.out.println("---------------------------------");

		st.close();
		con.close();

	}

	private static void Insert_Appointment() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----INSERT APPOINTMENT--------");

		System.out.println("Enter the Doctor ID");
		int d_id = sc.nextInt();
		System.out.println("Enter your Patient ID");
		int p_id = sc.nextInt();
		System.out.println("Enter the Date(DD/MM/YYYY)");
		String a_date = sc.next();
		System.out.println("Enter the Time in 24 hour format(15.00)");
		String a_time = sc.next();
		System.out.println("Enter the Reason");
		String a_reason = sc.next();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		String insertQuery = "insert into Appointments(doc_id, pat_id,app_date, app_time, reason) " + "values(" + d_id
				+ ","
				+ p_id + ",'" + a_date
				+ "','" + a_time + "','" + a_reason + "');";
		int res = st.executeUpdate(insertQuery);

		if (res == 0) {
			System.out.println("\nAppointment not inserted");
		} else {
			System.out.println("\nAppointment inserted successfully");
		}
		System.out.println("---------------------------------");

		st.close();
		con.close();

	}

	public static void Modify_Appointment() throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("--------------MODIFY APPOINTMENT DETAILS-------------- ");
		System.out.println("1. Update the Doctor ");
		System.out.println("2. Update the Patient ");
		System.out.println("3. Update Date");
		System.out.println("4. Update Time");
		System.out.println("5. Update the reason");

		System.out.println("0. Exit\n");

		System.out.println("Enter your choice: ");
		int ch = sc.nextInt();
		int a_id;

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();

		switch (ch) {
		case 1:
			System.out.println("Enter Appointment Id: ");
			a_id = sc.nextInt();
			System.out.println("Enter the Doctor ID:- ");
			int d_id = sc.nextInt();
			String UpdDocQuery = " update appointments set doc_id = " + d_id + " where app_id =" + a_id + ";";
			int res1 = st.executeUpdate(UpdDocQuery);

			if (res1 == 0) {
				System.out.println("\nAppointment not updated\n");
			} else {
				System.out.println("\nAppointment updated succesfully\n");
			}
			break;

		case 2:
			System.out.println("Enter Appointment Id: ");
			a_id = sc.nextInt();
			System.out.println("Enter the Patient ID:- ");
			int p_id = sc.nextInt();
			String UpdPatQuery = " update appointments set pat_id = " + p_id + " where app_id =" + a_id + ";";
			int res2 = st.executeUpdate(UpdPatQuery);

			if (res2 == 0) {
				System.out.println("\nAppointment not updated\n");
			} else {
				System.out.println("\nAppointment updated succesfully\n");
			}
			break;

		case 3:
			System.out.println("Enter Appointment Id: ");
			a_id = sc.nextInt();
			System.out.println("Enter the new date");
			String a_date = sc.next();
			String UpdDateQuery = " update appointments set app_date = '" + a_date + "'where app_id =" + a_id + ";";
			int res3 = st.executeUpdate(UpdDateQuery);

			if (res3 == 0) {
				System.out.println("\nAppointment not updated\n");
			} else {
				System.out.println("\nAppointment updated succesfully\n");
			}
			break;

		case 4:
			System.out.println("Enter Appointment Id: ");
			a_id = sc.nextInt();
			System.out.println("Enter the new timing ):- ");
			String a_time = sc.next();
			String UpdTimeQuery = " update appointments set  app_time= '" + a_time + "'where app_id =" + a_id + ";";
			int res4 = st.executeUpdate(UpdTimeQuery);

			if (res4 == 0) {
				System.out.println("\nAppointment not updated\n");
			} else {
				System.out.println("\nAppointment updated succesfully\n");
			}

			break;

		case 5:
			System.out.println("Enter Appointment Id: ");
			a_id = sc.nextInt();
			System.out.println("Enter the reason ):- ");
			String a_reason = sc.next();
			String UpdResQuery = " update appointments set reason = '" + a_reason + "'where app_id =" + a_id + ";";
			int res5 = st.executeUpdate(UpdResQuery);

			if (res5 == 0) {
				System.out.println("\nAppointment not updated\n");
			} else {
				System.out.println("\nAppointment updated succesfully\n");
			}
			break;


		case 0:
			break;

		default:
			System.out.println("Invalid Choice!!!\n");
		}

		st.close();
		con.close();
		// sc.close();
	}




	private static void displayMenu() {
		System.out.println("You are in the Appointments Menu. Enter an option");
		System.out.println(
				"\n1 - Make an Appointment\n2 - Modify Appointment Details\n3 - Delete Appointment\n4 - View an appointment\n"
						+ "5 - View All appointments\n0 - Exit");

	}

}
