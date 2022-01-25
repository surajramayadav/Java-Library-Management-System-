package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminBookSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	Admin admin = null;
	String book_name = null;
	String book_isbn = null;
	String book_author = null;
	String genre_type = null;
	int book_id = 0;
	int genre_id = 0;
	int book_qaunatity = 0;
	ScannerInput sc = null;

	public AdminBookSwitch() {
		admin = new Admin();
		log = LogManager.getLogger(AdminSwitch.class.getName());
		sc = new ScannerInput();
		ps = new PrintStatement();
	}

	public void adminBookSwitch() {
		try {
			log = LogManager.getLogger(AdminBookSwitch.class.getName());
			clearConsole = new ClearConsole();
			ScannerInput sc = new ScannerInput();
			ps = new PrintStatement();
//			clearConsole.clearConsole();
			ps.printData("Book");
			ps.printData("1) Search");
			ps.printData("2) Add");
			ps.printData("3) View");
			ps.printData("4) Update");
			ps.printData("5) Delete");
			ps.printData("6) Back");
			ps.printData("7) Exit");

			int adminOption = ps.printIntOption();
			switch (adminOption) {
			case 1:
				book_name = sc.getStringInput("Enter Book Name : ");
				if(!admin.bookSearch(book_name)) {
					ps.printData("");
					ps.printData("Book is Not Exits ");
				}
				
				adminBookSwitch();
				break;
			case 2:
				book_name = sc.getStringInput("Enter Book Name : ");
				String bookId=admin.checkBookAlredyExits(book_name);
				if( bookId != "false") {
					ps.printData("");
					book_id=Integer.parseInt(bookId);
					ps.printData("Book is alredy Exits ");
					int quantity=admin.getBookQuantity(book_id);
					book_qaunatity = sc.getIntInput("Book is alredy Exits so Enter Quantity : ");
					int sum=quantity+book_qaunatity;
					if(admin.bookQuantityUpdate(book_id, sum)) {
						ps.printData("Book Added Successfully");
					}
					adminBookSwitch();
				}else {
					ps.printData("Book is Not Exits So Add New Book");
					book_isbn = sc.getStringInput("Enter Book ISBN Number : ");
					book_author = sc.getStringInput("Enter Book Author Name : ");
					book_qaunatity = sc.getIntInput("Enter Book Quantity : ");
					genre_type = sc.getStringInput("Enter Genre Type : ");
					if (admin.genreSearch(genre_type)) {
						genre_id = admin.genreGetId(genre_type);
						if (admin.bookAdd(book_name, book_isbn, book_qaunatity, book_author, genre_id)) {
							ps.printData("Book Added Successfully");
							adminBookSwitch();
						}
							adminBookSwitch();
						
					} else {
						if (admin.genreAdd(genre_type)) {
							genre_id = admin.genreGetId(genre_type);
							if (admin.bookAdd(book_name, book_isbn, book_qaunatity, book_author, genre_id)) {
								ps.printData("Book Added Successfully");
								adminBookSwitch();
							}
								adminBookSwitch();
							
						}
					}
				}
				

				break;
			case 3:
				admin.bookView();
				ps.printData("");
				adminBookSwitch();
				break;
			case 4:
				bookUpdateSwitch();
				break;
			case 5:
				book_name = sc.getStringInput("Enter Book Name : ");
				if(admin.bookSearch(book_name)) {
					ps.printData("");
					book_id=sc.getIntInput("Enter Book Id : ");
					if(admin.issuedBookDeleteBook(book_id)) {
						if(admin.bookDelete(book_id))
						{
							ps.printData("Book Deleted Successfully ");
						}
					}
					
				}else {
					ps.printData("Book is Not Exits ");
				}
				
				adminBookSwitch();
				break;
			case 6:
				clearConsole.clearConsole();
				AdminHome welcomeAdmin = new AdminHome();
				welcomeAdmin.adminHomeSwitch();
				break;
			case 7:
				clearConsole.clearConsole();
				ps.printExit();
				System.exit(0);
				break;

			default:
				clearConsole.clearConsole();
				ps.printData("Wrong option !!!");
				adminBookSwitch();
				break;
			}
		} catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			adminBookSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			adminBookSwitch();
		}

	}

	public void bookUpdateSwitch() {

		try {
			ps.printData("Book Update");
			ps.printData("1) Change Book Name");
			ps.printData("2) Change Book ISBN Number");
			ps.printData("3) Change Book Quantity");
			ps.printData("4) Change Book Author Name");
			ps.printData("5) Back");
			ps.printData("6) Exit");

			int adminOption = ps.printIntOption();
			switch (adminOption) {
			case 1:
				book_name = sc.getStringInput("Enter Book Name : ");
				if(admin.bookSearch(book_name)) {
					ps.printData("");
					book_id = sc.getIntInput("Enter Book Id : ");
					book_name = sc.getStringInput("Enter Book New Name : ");
					if(admin.bookNameUpdate(book_id, book_name)) {
						ps.printData("Book Name Updated Successfully");
					}else{
						ps.printData("Something Went Wrong !!!");
					}
				}else {
					ps.printData("Book Not Found");
				}
				
				bookUpdateSwitch();
				break;
			case 2:
				book_name = sc.getStringInput("Enter Book Name : ");
				if(admin.bookSearch(book_name)) {
					ps.printData("");
					book_id = sc.getIntInput("Enter Book Id : ");
					book_isbn = sc.getStringInput("Enter Book New ISBN Number : ");
					if(admin.bookISBNUpdate(book_id, book_isbn)) {
						ps.printData("Book ISBN Number Updated Successfully");
					}else{
						ps.printData("Something Went Wrong !!!");
					}
				}else{
					ps.printData("Book Not Found");
				}
				
				bookUpdateSwitch();
				break;
			case 3:
				book_name = sc.getStringInput("Enter Book Name : ");
				if(admin.bookSearch(book_name)) {
					ps.printData("");
					book_id = sc.getIntInput("Enter Book Id : ");
					book_qaunatity = sc.getIntInput("Enter Book Quantity : ");
					if(admin.bookQuantityUpdate(book_id, book_qaunatity)) {
						ps.printData("Book Quantity Updated Successfully");
					}else{
						ps.printData("Something Went Wrong !!!");
					}
				}
				
				else{
					ps.printData("Book Not Found");
				}
				bookUpdateSwitch();
				break;
			case 4:
				book_name = sc.getStringInput("Enter Book Name : ");
				if(admin.bookSearch(book_name)) {
					ps.printData("");
					book_id = sc.getIntInput("Enter Book Id : ");
					book_author = sc.getStringInput("Enter Book Author Name : ");
					if(admin.bookAuthorUpdate(book_id, book_author)) {
						ps.printData("Book Author Updated Successfully");
					}else{
						ps.printData("Something Went Wrong !!!");
					}	
				}
				else{
					ps.printData("Book Not Found");
				}
				bookUpdateSwitch();
				break;

			case 5:
				clearConsole.clearConsole();
				adminBookSwitch();
				break;
			case 6:
				clearConsole.clearConsole();
				ps.printExit();
				System.exit(0);
				break;

			default:
				clearConsole.clearConsole();
				ps.printOptionErr();
				bookUpdateSwitch();
				break;
			}
		} catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			bookUpdateSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			bookUpdateSwitch();
		}

	}
}
