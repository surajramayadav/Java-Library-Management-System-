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

public interface Genre {

	Logger log = LogManager.getLogger(Admin.class.getName());
	PrintStatement ps = new PrintStatement();

	default boolean genreAdd(String genre_type) {
		boolean flag = false;
		String sql = "insert into genre (genre_type) values(?)";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, genre_type);
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

	default boolean genreDelete(int genre_id) {
		boolean flag = false;
		String sql = "DELETE FROM `genre` WHERE genre_id = ?";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, genre_id);
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

	default int genreGetId(String genre_type) {
		int genre_id = 0;
		try {
			String sql = "select genre_id from genre where genre_type = '" + genre_type + "'";
			Connection connection = DatabaseHelper.openConnection();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				genre_id = resultSet.getInt(1);
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
			System.out.println("Invalid Input");
		} catch (Exception ex) {
//				e.printStackTrace();
			log.error(ex.getMessage());
		}
		return genre_id;
	}

	default boolean genreSearch(String genre_type) {
		boolean flag = false;
		int genre_id = 0;
		try {
			String sql = "select genre_id from genre where genre_type = '" + genre_type + "'";
			Connection connection = DatabaseHelper.openConnection();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				genre_id = resultSet.getInt(1);
			}
			if (genre_id == 0) {
				flag = false;
			} else {
				flag = true;
			}

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
}
