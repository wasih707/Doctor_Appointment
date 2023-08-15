package Appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctors {
	public static void PrintDetails() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root",
				"The#1password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Doctors");
		System.out.println("------DOCTOR DETAILS--------");
		System.out.println("ID Specialisation Name Timing\n-----------------------------------\n");

		while (rs.next()) {
			System.out.println(rs.getInt("doc_id")
					+ " " +
					rs.getString("specialisation") + " " + rs.getString("name") + " " + rs.getString("timing"));

		}
		System.out.println("\n\n");
		st.close();
		con.close();

	}

}
