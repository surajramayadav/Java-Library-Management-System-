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
import libraryManagementSystem.utils.CrytoGraphy;
import libraryManagementSystem.utils.PrintStatement;

public class User {

	static Logger log = null;
	PrintStatement ps = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	Statement statement;
	CrytoGraphy crytoGraphy=null;

	public User() {
		try {
			log = LogManager.getLogger(Admin.class.getName());
			ps = new PrintStatement();
			crytoGraphy=new CrytoGraphy();
			connection = DatabaseHelper.openConnection();
		} catch (ClassNotFoundException | SQLException e) {

			log.error(e.getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}

	public String userLogin(String username, String password) {
		String user_id="false";
		String sql = "select * from user where user_phone=?";
		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if(resultSet.getString(5) != null) {
					String deCodePassword=crytoGraphy.getDecrpytedData(resultSet.getString(5));
					if(deCodePassword.equals(password)) {
						user_id=String.valueOf(resultSet.getInt(1));
					}
				}
//				
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//			e.printStackTrace();
			log.error(ex.getMessage());
		}
		
		return user_id;
	}

	public boolean userIssuedUserIdSearch(int user_id) {
		ps.printData("");
		System.out.printf("%-6s%-15s%-15s%-10s%-20s%-15s%-15s\n","Id","Issued Date","Return Date","Status","Book Name","User Name", "Admin Username");
		boolean isUserIssuedBook =false;
		String sql = "select issued_book.issuedbook_id, issued_book.issued_date,issued_book.return_date,issued_book.return_status,book.book_name,user.user_name,admin.admin_username from issued_book\n"
				+ "inner join book on issued_book.book_id = book.book_id\n"
				+ "inner join user on issued_book.user_id = user.user_id\n"
				+ "inner join admin on issued_book.admin_id = admin.admin_id where user.user_id = "+user_id+"";
		try {
			connection = DatabaseHelper.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				isUserIssuedBook=true;
				System.out.printf("%-6s%-15s%-15s%-10s%-20s%-15s%-15s\n",String.valueOf(resultSet.getInt(1)),resultSet.getString(2).substring(0,10),resultSet.getString(3).substring(0,10),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6), resultSet.getString(7));
//				ps.printData("");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(2));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(3));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(4));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(5));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(6));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(7));
//				ps.printData("");
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}
		return isUserIssuedBook;
	}

	public boolean userBookSearch(String book_name) {
		boolean isBook =false;
		ps.printData("");
		System.out.printf("%-6s%-20s%-15s%-10s%-20s%-10s\n","Id","Book Name","Isbn","Quantity","Author","Genre");
		try {
			 connection = DatabaseHelper.openConnection();
			 String sql = "select book.book_id,book.book_name,book.book_isbn,book.book_quantity,book.book_author,genre.genre_type from book inner join genre on book.genre_id = genre.genre_id  where book.book_name like '" + book_name + "%'";
			 statement = connection.createStatement();
			 resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				isBook=true;
				System.out.printf("%-6s%-20s%-15s%-10s%-20s%-10s\n",String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),String.valueOf(resultSet.getInt(4)),resultSet.getString(5),resultSet.getString(6));
//				ps.printData("");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(2));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(3));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(5));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(6));
//				ps.printData("");
			}

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return isBook;
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
