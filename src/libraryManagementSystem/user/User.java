package libraryManagementSystem.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.PrintStatement;

public class User {

	static Logger log = null;
	PrintStatement ps = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement;

	public User() {
		try {
			log = LogManager.getLogger(Admin.class.getName());
			ps = new PrintStatement();
			connection = DatabaseHelper.openConnection();
		} catch (ClassNotFoundException | SQLException e) {

			log.error(e.getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}

	public String userLogin(String username, String password) {

		String sql = "select * from user where user_phone=? and user_password=?";
		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
//				System.out.print("success");
				return String.valueOf(resultSet.getInt(1));
			} else {
//				System.out.print("failed");
				return "false";
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//			e.printStackTrace();
			log.error(ex.getMessage());
		}
		System.out.print("error");
		return "error";
	}

	public void userIssuedUserIdSearch(int user_id) {

		String sql = "select * from issued_book where user_id = '" + user_id + "'";
		try {
			connection = DatabaseHelper.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ps.printData("");
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(2));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(3));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(4));

				ps.printData("");
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}

	}

	public void userBookSearch(String book_name) {
		try {
			 connection = DatabaseHelper.openConnection();
			String sql = "select * from book where book_name like '" + book_name + "%'";
			 statement = connection.createStatement();
			 resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ps.printData("");
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(2));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(3));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(5));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
				ps.printData("");
			}

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}

	}

	public boolean userChangePassword(int user_id, String password) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update user set user_password='" + password + "' where user_id=" + user_id + "; ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return flag;
	}
	
	

}
