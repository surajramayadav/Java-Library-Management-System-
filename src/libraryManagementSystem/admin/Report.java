package libraryManagementSystem.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.PrintStatement;

public interface Report {

	Logger log = LogManager.getLogger(Admin.class.getName());
	PrintStatement ps = new PrintStatement();
	
	default boolean returnBookToday() {
		boolean isReturnBookToday = false;
		System.out.printf("%-20s%-15s\n","Book Name","User name");

		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select  book.book_name,user.user_name from issued_book\n"
					+ "inner join book on issued_book.book_id = book.book_id\n"
					+ "inner join user on issued_book.user_id = user.user_id\n"
					+ "where SUBSTRING(issued_book.return_date, 1, 10)= SUBSTRING(CURRENT_TIMESTAMP, 1, 10);";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
					isReturnBookToday = true;
					System.out.printf("%-20s%-20s\n",resultSet.getString(1),resultSet.getString(2));			
					
				}

				
		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return isReturnBookToday;

	}
	
	default boolean countBookByGenre() {
		boolean isCountBookByGenre = false;
		try {
			System.out.printf("%-15s%-10s\n","Genre","Count");
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select genre.genre_type,count(*) as count from book inner join genre on book.genre_id = genre.genre_id group by genre.genre_type;\n"
					+ "";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
					isCountBookByGenre = true;
					System.out.printf("%-15s%-10s\n",resultSet.getString(1),resultSet.getString(2));
				}
				
		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}
		return isCountBookByGenre;

	}
	
	
}
