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
		String sql = "insert into user(user_name,user_phone,user_address) values('" + user_name + "','" + user_phone
				+ "','" + user_address + "')";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch(SQLIntegrityConstraintViolationException sq) {
			ps.printData("User Phone Number is already Exits ");
			log.error(sq.getMessage());
		}catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	default boolean userDelete(int user_id) {
		boolean flag = false;
		String sql = "DELETE FROM `user` WHERE user_id = '" + user_id + "'";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	default boolean userSearch(String user_name) {
		boolean isUser = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select * from user where user_name like '" + user_name + "%'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				isUser = true;
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
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return isUser;
	}

	default void userView() {
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select * from user";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
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
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}

	}

	default boolean userPhoneUpdate(int user_id, String user_phone) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update user set user_phone='" + user_phone + "' where user_id=" + user_id + "; ";
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
