package libraryManagementSystem.admin;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.LoginSwitch;
import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.PrintStatement;

public class Admin {
	static Logger log = null;
	PrintStatement ps = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement;

	public Admin() {
		log = LogManager.getLogger(Admin.class.getName());
		ps = new PrintStatement();
	}
	
	public String adminLogin(String admin_username,String admin_password) {
		
		String sql = "select * from admin where admin_username=? and admin_password=?";
		try {
			connection = DatabaseHelper.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,admin_username );
			preparedStatement.setString(2, admin_password);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
//				System.out.print("sucess");
				return "true";
			} else {
//				System.out.print("failed");
				return "false";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		}
		System.out.print("error");
		return "error";
	}
	

	public boolean adminAdd(String admin_username,String admin_password,String admin_role) {
		
		boolean flag = false;
		String sql = "INSERT INTO `admin` ( `admin_username`, `admin_password`, `admin_role`) VALUES('"
				+ admin_username + "','" + admin_password + "','" + admin_role + "')";
		try {
			connection = DatabaseHelper.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		}
		return flag;
	}
}
