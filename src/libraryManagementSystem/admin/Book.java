package libraryManagementSystem.admin;

import java.sql.*;

import libraryManagementSystem.database.DatabaseHelper;

public interface Book extends Genre {

	default boolean bookAdd(String book_name, String book_isbn, int book_quantity, String book_author, int genre_id ) {
		boolean flag = false;
		String sql = "insert into book (book_name,book_isbn,book_quantity,book_author,genre_id) values (?,?,?,?,?)";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book_name);
			preparedStatement.setString(2, book_isbn);
			preparedStatement.setInt(3, book_quantity);
			preparedStatement.setString(4, book_author);
			preparedStatement.setInt(5, genre_id);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLIntegrityConstraintViolationException sq) {
			ps.printData("Book ISBN Already Exits. So Enter New ISBN Number");
			log.error(sq.getMessage());
		}
		catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
			System.out.println("Invalid Input");
		} catch (Exception ex) {
//				ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	default boolean bookDelete(int book_id) {
		boolean flag = false;
		String sql = "DELETE FROM `book` WHERE book_id = ?";
		try {
			Connection connection = DatabaseHelper.openConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, book_id);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLIntegrityConstraintViolationException sd) {
		    System.out.println("Book ISBN Alredy Exits ");
		} 
		catch (SQLException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
			System.out.println("Invalid Input");
		} catch (Exception ex) {
//				ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return flag;
	}

	default boolean bookSearch(String book_name) {
		boolean isBook = false;
		
		System.out.printf("%-6s%-20s%-15s%-10s%-20s%-10s\n","Id","Book Name","Isbn","Quantity","Author","Genre");
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select book.book_id,book.book_name,book.book_isbn,book.book_quantity,book.book_author,genre.genre_type from book inner join genre on book.genre_id = genre.genre_id  where book.book_name like '" + book_name + "%'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
					isBook = true;
					System.out.printf("%-6s%-20s%-15s%-10s%-20s%-10s\n",String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),String.valueOf(resultSet.getInt(4)),resultSet.getString(5),resultSet.getString(6));
//					ps.printData("");
//					ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
//					ps.printDataWithoutLN(" | ");
//					ps.printDataWithoutLN(resultSet.getString(2));
//					ps.printDataWithoutLN(" | ");
//					ps.printDataWithoutLN(resultSet.getString(3));
//					ps.printDataWithoutLN(" | ");
//					ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
//					ps.printDataWithoutLN(" | ");
//					ps.printDataWithoutLN(resultSet.getString(5));
//					ps.printDataWithoutLN(" | ");
//					ps.printDataWithoutLN(resultSet.getString(6));
//					ps.printData("");
					
				}

				
		} catch (SQLException e) {
			log.error(e.getMessage());
			System.out.println("Invalid Input");

		
		}
		catch (Exception ex) {
			log.error(ex);
		}
		
		return isBook;

	}

	default int getBookQuantity(int book_id) {
		int quantity = 0;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select book_quantity from book where book_id = '" + book_id + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				quantity = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			log.error(e);
			System.out.println("Invalid Input");

		} catch (Exception ex) {
			log.error(ex);
		}

		return quantity;
	}

	default void bookView() {
		
		System.out.printf("%-6s%-20s%-15s%-10s%-20s%-10s\n","Id","Book Name","Isbn","Quantity","Author","Genre");
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "select book.book_id,book.book_name,book.book_isbn,book.book_quantity,book.book_author,genre.genre_type from book inner join genre on book.genre_id = genre.genre_id";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
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
			System.out.println("Invalid Input");

		} catch (Exception ex) {
			log.error(ex);
		}

	}

	default boolean bookNameUpdate(int book_id, String book_name) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_name=? where book_id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book_name);
			preparedStatement.setInt(2, book_id);
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

	default boolean bookISBNUpdate(int book_id, String book_isbn) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_isbn=? where book_id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book_isbn);
			preparedStatement.setInt(2, book_id);
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

	default boolean bookQuantityUpdate(int book_id, int book_quantity) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_quantity=? where book_id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, book_quantity);
			preparedStatement.setInt(2, book_id);
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

	default boolean bookAuthorUpdate(int book_id, String book_author) {
		boolean flag = false;
		try {
			Connection connection = DatabaseHelper.openConnection();
			String sql = "update book set book_author=? where book_id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book_author);
			preparedStatement.setInt(2, book_id);
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
	


	default String checkBookAlredyExits(String book_name) {
	String isBookExits = "false";
	System.out.printf("%-6s%-20s%-15s%-10s%-20s%-10s\n","Id","Book Name","Isbn","Quantity","Author","Genre");
	try {
		Connection connection = DatabaseHelper.openConnection();
		String sql = "select * from book where book_name = '" + book_name + "'";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
//				ps.printData("");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(1)));
				isBookExits = String.valueOf(resultSet.getInt(1));
				System.out.printf("%-6s%-20s%-15s%-10s%-20s%-10s\n",String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(3),String.valueOf(resultSet.getInt(4)),resultSet.getString(5),resultSet.getString(6));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(2));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(3));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(4)));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(resultSet.getString(5));
//				ps.printDataWithoutLN(" | ");
//				ps.printDataWithoutLN(String.valueOf(resultSet.getInt(6)));
				ps.printData("");
				
			}
			


	} catch (SQLException e) {
		log.error(e);
		System.out.println("Invalid Input");

	} catch (Exception ex) {
		log.error(ex);
	}
	return isBookExits;

}
}

