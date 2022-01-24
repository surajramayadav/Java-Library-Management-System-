package libraryManagementSystem.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "insert into issued_book(return_date,book_id,admin_id,user_id) values(DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 10 DAY),"+book_id+","+admin_id+","+user_id+")";
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
	
	default void issuedBookIdSearch(int book_id) {
		
		String sql = "select * from issued_book where book_id = '"+book_id+"'";
		try {
			Connection connection = DatabaseHelper.openConnection();
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
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}

	}


	default void issuedView() {
		
		String sql = "select * from issued_book ";
		try {
			Connection connection = DatabaseHelper.openConnection();
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
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}

	}


	default void issuedUserIdSearch(int user_id) {
		
		String sql = "select * from issued_book where user_id = '"+user_id+"'";
		try {
			Connection connection = DatabaseHelper.openConnection();
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
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}

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
}
