package libraryManagementSystem.admin;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.LoginSwitch;
import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.CrytoGraphy;
import libraryManagementSystem.utils.PrintStatement;

public class Admin implements Book,IssuedBook,User,Report {
	static Logger log = null;
	PrintStatement ps = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement;
	CrytoGraphy crytoGraphy=null;

	public Admin() {
		try {
			log = LogManager.getLogger(Admin.class.getName());
			ps = new PrintStatement();
			connection = DatabaseHelper.openConnection();
			 crytoGraphy=new CrytoGraphy();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Invalid Input");
			log.error(e.getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}

	public String adminLogin(String admin_username, String admin_password) {

		String admin_id="false";
		String sql = "select * from admin where admin_username=?";
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin_username);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if(resultSet.getString(3)!=null) {
					String deCodePassword=crytoGraphy.getDecrpytedData(resultSet.getString(3));
//					ps.printData(deCodePassword);
					if(admin_password.equals(deCodePassword)) {
						admin_id= String.valueOf(resultSet.getInt(1));
					}
					
				}
//				System.out.print("success");
				
			} 

		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
			System.out.println("Invalid Input");
		} catch (Exception ex) {
//			e.printStackTrace();
			log.error(ex.getMessage());
		}
	
		return admin_id;
	}

	public boolean adminAdd(String admin_username, String admin_password, String admin_role) {

		boolean flag = false;
		String sql = "INSERT INTO `admin` ( `admin_username`, `admin_password`, `admin_role`) VALUES(?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin_username);
			preparedStatement.setString(2, admin_password);
			preparedStatement.setString(3, admin_role);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
			System.out.println("Invalid Input");
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	public boolean adminDelete(int admin_id) {
		boolean flag = false;
		try {
			String sql1 = "DELETE FROM `admin` WHERE admin_id = ?";
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, admin_id);
			preparedStatement.executeUpdate();

			flag = true;
		} catch (SQLException e) {
			System.out.println("Invalid Input");
			log.error(e);
		} catch (Exception ex) {

			log.error(ex.getMessage());
		}
		return flag;
	}

	public boolean adminSearch(String admin_username) {
		boolean isAdmin=false;
		System.out.printf("%-6s%-15s%-10s\n","Id","Username","Role");
		try {
			String sql = "select * from admin where admin_username like '" + admin_username + "%'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		
			while (resultSet.next()) {
				isAdmin=true;
				System.out.printf("%-6s%-15s%-10s\n", String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(4));
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(2));
////				ps.printDataWithoutLN(" | ");
////				ps.printDataWithoutLN(resultSet.getString(3));
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
		return isAdmin;

	}

	public void adminView() {
		try {
			System.out.printf("%-6s%-15s%-10s\n","Id","Username","Role");
			String sql = "select * from admin";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.printf("%-6s%-15s%-10s\n", String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(4));
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

	public boolean adminUserNameUpdate(int admin_id, String admin_username) {
		boolean flag = false;
		try {
			String sql = "update admin set admin_username=? where admin_id=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin_username);
			preparedStatement.setInt(2, admin_id);
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
			String sql = "update admin set admin_password=? where admin_id=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin_password);
			preparedStatement.setInt(2, admin_id);
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

	public boolean adminRoleUpdate(int admin_id, String admin_role) {
		boolean flag = false;
		try {
			String sql = "update admin set admin_role=? where admin_id=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin_role);
			preparedStatement.setInt(2, admin_id);
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
			System.out.println("Invalid Input");
		} catch (Exception ex) {
//			e.printStackTrace();
			log.error(ex.getMessage());
		}
		System.out.print("error");
		return "error";
	}
}