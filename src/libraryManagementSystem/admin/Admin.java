package libraryManagementSystem.admin;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.LoginSwitch;
import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.PrintStatement;

public class Admin implements Book,IssuedBook,User {
	static Logger log = null;
	PrintStatement ps = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement;
	

	public Admin() {
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

	public String adminLogin(String admin_username, String admin_password) {

		String sql = "select * from admin where admin_username=? and admin_password=?";
		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin_username);
			preparedStatement.setString(2, admin_password);
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

	public boolean adminAdd(String admin_username, String admin_password, String admin_role) {

		boolean flag = false;
		String sql = "INSERT INTO `admin` ( `admin_username`, `admin_password`, `admin_role`) VALUES('" + admin_username
				+ "','" + admin_password + "','" + admin_role + "')";
		try {
			preparedStatement = connection.prepareStatement(sql);
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

	public boolean adminDelete(int admin_id) {
		boolean flag = false;
		try {
			String sql1 = "DELETE FROM `admin` WHERE admin_id = '" + admin_id + "'";
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.executeUpdate();

			flag = true;
		} catch (SQLException e) {
			log.error(e);
		} catch (Exception ex) {

			log.error(ex.getMessage());
		}
		return flag;
	}

	public void adminSearch(String admin_username) {
		try {
			String sql = "select * from admin where admin_username like '" + admin_username + "%'";
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
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}

	}

	public void adminView() {
		try {
			ps.printData("");
			String sql = "select * from admin";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
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

	public boolean adminUserNameUpdate(int admin_id, String admin_username) {
		boolean flag = false;
		try {
			String sql = "update admin set admin_username='" + admin_username + "' where admin_id=" + admin_id + "; ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return flag;
	}

	public boolean adminPasswordUpdate(int admin_id, String admin_password) {
		boolean flag = false;
		try {
			String sql = "update admin set admin_password='" + admin_password + "' where admin_id=" + admin_id + "; ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return flag;
	}

	public boolean adminRoleUpdate(int admin_id, String admin_role) {
		boolean flag = false;
		try {
			String sql = "update admin set admin_role='" + admin_role + "' where admin_id=" + admin_id + "; ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return flag;
	}


	public String getSuperAdminInfo(int admin_id) {

		String sql = "select admin_role from admin where admin_id=?";
		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, admin_id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
//				System.out.print("success");
				return resultSet.getString(1);
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
}