package com.myclass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	// database inform
		private static String url = "jdbc:mysql://localhost:3307/CRM_APP";
		private static String username = "root";
		private static String password = "1234";
		
		//get connection method
		public static Connection getConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				return DriverManager.getConnection(url, username, password);
			}
			catch (ClassNotFoundException e) {
				System.out.println("Khong doc duoc driver JDBC");
			}
			catch (SQLException exc) {
				System.out.println("Loi ket noi database. Kiem tra username, password");
			}
			return null;
		}
}
