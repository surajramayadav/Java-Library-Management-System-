package libraryManagementSystem.admin;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.PrintStatement;

public interface User {
	Logger log = LogManager.getLogger(Admin.class.getName());
	PrintStatement ps = new PrintStatement();

	default boolean userAdd(String user_name, String user_phone, String user_address) {
		boolean flag = false;
		String sql = "insert into user(user_name,user_phone,user_address) values(?,?,?)";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_name);
			preparedStatement.setString(2, user_phone);
			preparedStatement.setString(3, user_address);
			
			preparedStatement.executeUpdate();
			flag = true;
		} catch(SQLIntegrityConstraintViolationException sq) {
			ps.printData("User Phone Number is already Exits ");
			log.error(sq.getMessage());
		}catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Invalid Input");
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	default boolean userDelete(int user_id) {
		boolean flag = false;
		String sql = "DELETE FROM `user` WHERE user_id =?";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Invalid Input");
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	default boolean userSearch(String user_name) {
		boolean isUser = false;
//		ps.printData("");
		System.out.printf("%-6s%-15s%-12s%-10s\n","Id","User Name","Phone No","Address");
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select * from user where user_name like '" + user_name + "%'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				isUser = true;
				System.out.printf("%-6s%-15s%-12s%-10s\n",String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
//				ps.printData("");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(2));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(3));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(4));
//
//				ps.printData("");
			}

		} catch (SQLException e) {
			log.error(e);
			System.out.println("Invalid Input");
		} catch (Exception ex) {
			log.error(ex);
		}
		return isUser;
	}

	default void userView() {
		try {
//			ps.printData("");
			System.out.printf("%-6s%-15s%-12s%-10s\n","Id","User Name","Phone No","Address");
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select * from user";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.printf("%-6s%-15s%-12s%-10s\n",String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
//				ps.printData("");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(2));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(3));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(4));
//				ps.printData("");
			}

		} catch (SQLException e) {
			log.error(e);
			System.out.println("Invalid Input");

		} catch (Exception ex) {
			log.error(ex);
		}

	}

	default boolean userPhoneUpdate(int user_id, String user_phone) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update user set user_phone=? where user_id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user_phone);
			preparedStatement.setInt(2, user_id);
			preparedStatement.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			log.error(e);
			System.out.println("Invalid Input");

		} catch (Exception ex) {
			log.error(ex);
		}
		return flag;
	}

}
