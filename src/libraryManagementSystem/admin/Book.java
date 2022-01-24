package libraryManagementSystem.admin;


import java.sql.*;

import libraryManagementSystem.database.DatabaseHelper;

public interface Book extends Genre{

	default boolean bookAdd(String book_name,String book_isbn,int book_quantity,String book_author,int genre_id) {
		boolean flag = false;
		String sql = "insert into book (book_name,book_isbn,book_quantity,book_author,genre_id) values "
				+ "('"+book_name+"','"+book_isbn+"','"+book_quantity+"','"+book_author+"','"+genre_id+"')";
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
	
	default boolean bookDelete(int book_id) {
		boolean flag = false;
		String sql = "DELETE FROM `book` WHERE book_id = '" + book_id + "'";
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


	default void bookSearch(String book_name) {
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select * from book where book_name like '" + book_name + "%'";
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
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(5));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
				ps.printData("");
			}

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}

	}

	default void bookView() {
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select * from book";
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
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(resultSet.getString(5));
				ps.printDataWithoutLN(" | ");
				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(6)));
				ps.printData("");
			}

		} catch (SQLException e) {
			log.error(e);

		} catch (Exception ex) {
			log.error(ex);
		}

	}
	
	default boolean bookNameUpdate(int book_id, String book_name) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_name='" + book_name + "' where book_id=" + book_id + "; ";
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
	
	default boolean bookISBNUpdate(int book_id, String book_isbn) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_isbn='" + book_isbn + "' where book_id=" + book_id + "; ";
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
	
	default boolean bookQuantityUpdate(int book_id, int book_quantity) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_quantity='" + book_quantity + "' where book_id=" + book_id + "; ";
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
	
	default boolean bookAuthorUpdate(int book_id,String book_author) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_author='" + book_author + "' where book_id=" + book_id + "; ";
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

