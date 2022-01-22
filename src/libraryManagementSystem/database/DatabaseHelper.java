package libraryManagementSystem.database;

import java.sql.*;
import java.sql.DriverManager;


public class DatabaseHelper {

	public static final String URL = "jdbc:mysql://localhost:3306/library_management";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "suraj";
	public static Connection connection = null;

	public static Connection openConnection() throws SQLException, ClassNotFoundException {
		if (connection != null) {
			return connection;
		} else {
			Class.forName(DRIVER);
//			System.out.println("loaded");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			System.out.println("connected");
		}
		return connection;
	}

}
