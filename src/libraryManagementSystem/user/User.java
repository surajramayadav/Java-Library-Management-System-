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

	public boolean userIssuedUserIdSearch(int user_id) {
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
				ps.printData("");
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(2));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(3));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(4));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(5));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(6));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(7));
				ps.printData("");
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
		try {
			 connection = DatabaseHelper.openConnection();
			 String sql = "select book.book_id,book.book_name,book.book_isbn,book.book_quantity,book.book_author,genre.genre_type from book inner join genre on book.genre_id = genre.genre_id  where book.book_name like '" + book_name + "%'";
			 statement = connection.createStatement();
			 resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				isBook=true;
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
				ps.printDataWithoutLN(resultSet.getString(6));
				ps.printData("");
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
