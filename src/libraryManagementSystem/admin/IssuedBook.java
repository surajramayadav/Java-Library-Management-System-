package libraryManagementSystem.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.PrintStatement;

public interface IssuedBook {
	
	Logger log = LogManager.getLogger(Admin.class.getName());
	PrintStatement ps = new PrintStatement();
	
	default boolean issuedBookADD(int admin_id,int user_id,int book_id) {
		boolean flag=false;
		String sql = "insert into issued_book(return_date,book_id,admin_id,user_id) values(DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 5 DAY),"+book_id+","+admin_id+","+user_id+")";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}
	
	default boolean issuedBookIdSearch(int book_id) {
//		ps.printData("");
		System.out.printf("%-6s%-15s%-15s%-10s%-20s%-15s%-15s\n","Id","Issued Date","Return Date","Status","Book Name","User Name", "Admin Username");
		boolean isIssuedBook =false;
		String sql = "select issued_book.issuedbook_id, issued_book.issued_date,issued_book.return_date,issued_book.return_status,book.book_name,user.user_name,admin.admin_username from issued_book\n"
				+ "inner join book on issued_book.book_id = book.book_id\n"
				+ "inner join user on issued_book.user_id = user.user_id\n"
				+ "inner join admin on issued_book.admin_id = admin.admin_id where book.book_id = "+book_id+"";
		try {
			Connection connection = DatabaseHelper.openConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				isIssuedBook=true;
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
		return isIssuedBook;

	}


	default void issuedView() {
//		ps.printData("");
		System.out.printf("%-6s%-15s%-15s%-10s%-20s%-15s%-15s\n","Id","Issued Date","Return Date","Status","Book Name","User Name", "Admin Username");
		String sql = "select issued_book.issuedbook_id,issued_book.issued_date,issued_book.return_date,issued_book.return_status,book.book_name,user.user_name,admin.admin_username from issued_book\n"
				+ "inner join book on issued_book.book_id = book.book_id\n"
				+ "inner join user on issued_book.user_id = user.user_id\n"
				+ "inner join admin on issued_book.admin_id = admin.admin_id;";
		try {
			Connection connection = DatabaseHelper.openConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
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

	}


	default boolean issuedUserIdSearch(int user_id) {
		ps.printData("");
		System.out.printf("%-6s%-15s%-15s%-10s%-20s%-15s%-15s\n","Id","Issued Date","Return Date","Status","Book Name","User Name", "Admin Username");
		 boolean isIssuedUser =false;
		String sql = "select issued_book.issuedbook_id, issued_book.issued_date,issued_book.return_date,issued_book.return_status,book.book_name,user.user_name,admin.admin_username from issued_book\n"
				+ "inner join book on issued_book.book_id = book.book_id\n"
				+ "inner join user on issued_book.user_id = user.user_id\n"
				+ "inner join admin on issued_book.admin_id = admin.admin_id where user.user_id = "+user_id+"";
		try {
			Connection connection = DatabaseHelper.openConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				isIssuedUser=true;
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

		return isIssuedUser;
	}

	default boolean issuedUserIdUpdate(int user_id,int book_id,String return_status) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update issued_book set return_status='" + return_status + "' where user_id=" + user_id + " AND book_id="+book_id+"";
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


	default boolean issuedBookDeleteUser(int user_id) {
		boolean flag = false;
		String sql = "DELETE FROM `issued_book` WHERE user_id = '" + user_id + "'";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLIntegrityConstraintViolationException sd) {
		    System.out.println("Book ISBN Alredy Exits ");
		} 
		catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}
	
	default boolean issuedBookDeleteAdmin(int admin_id) {
		boolean flag = false;
		String sql = "DELETE FROM `issued_book` WHERE admin_id = '" + admin_id + "'";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLIntegrityConstraintViolationException sd) {
		    System.out.println("Book ISBN Alredy Exits ");
		} 
		catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	default boolean issuedBookDeleteBook(int book_id) {
		boolean flag = false;
		String sql = "DELETE FROM `issued_book` WHERE book_id = '" + book_id + "'";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLIntegrityConstraintViolationException sd) {
		    System.out.println("Book ISBN Alredy Exits ");
		} 
		catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

}
