package Appointment;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			displayMenu();
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				Patients.Patient_Menu();

				break;
			case 2:
				try {
					Doctors.PrintDetails();
				} catch (Exception e) {
					System.out.println(e);
				}

				break;
			case 3:
				Appointments.Appointment_Menu();

				break;
			case 0:
				System.out.println("\n Exiting the program.");
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
				break;
			}
		} while (choice != 0);

		scanner.close();
	}

	private static void displayMenu() {
		System.out.println("-------THE DOCTOR APPOINTMENT SYSTEM---------");
		System.out.println("\n Welcome to the Doctor Appointment System\n");
		System.out.println("\n1 - Patients Menu \n");
		System.out.println("2 - Doctor Details \n");
		System.out.println("3 - Appointments Menu \n");
		System.out.println("0 - Exit\n");
		System.out.println("Please enter an option below:");

	}

}


