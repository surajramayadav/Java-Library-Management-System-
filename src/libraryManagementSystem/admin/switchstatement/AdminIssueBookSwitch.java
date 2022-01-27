package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.FileReadAndWrite;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminIssueBookSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	Admin admin = null;
	int admin_id = 0;
	int user_id = 0;
	int book_id = 0;
	String book_name = null;
	String user_name = null;
	ScannerInput sc = null;
	FileReadAndWrite fileReadAndWrite;

	public AdminIssueBookSwitch() {
		admin = new Admin();
		log = LogManager.getLogger(AdminSwitch.class.getName());
		sc = new ScannerInput();
		ps = new PrintStatement();
		fileReadAndWrite = new FileReadAndWrite();
	}

	public void adminIssueABookedSwitch() {

		try {
			log = LogManager.getLogger(AdminIssueBookSwitch.class.getName());
			clearConsole = new ClearConsole();
			ScannerInput sc = new ScannerInput();
			ps = new PrintStatement();
//			clearConsole.clearConsole();
			ps.printData("");
			ps.printData("Issue Book");
			ps.printData("1) Issue a Book");
			ps.printData("2) Return Issued Book");
			ps.printData("3) Search Issued Book By Book Id");
			ps.printData("4) Search Issued Book By User Id");
			ps.printData("5) View Issued Book ");
			ps.printData("6) Back");
			ps.printData("7) Exit");

			int adminOption = ps.printIntOption();
			switch (adminOption) {
			case 1:

				admin_id = Integer.parseInt(FileReadAndWrite.adminReadId());
				book_name = sc.getStringInput("Enter Book Name : ");
				// searching book
				if (admin.bookSearch(book_name)) {
					ps.printData("");
					
					book_id = sc.getIntInput("Enter Book Id : ");
					//get qaunatity of book by id
					int quantity = admin.getBookQuantity(book_id);
					//checking quantity for issueing book
					if (quantity > 0) {
						user_name = sc.getStringInput("Enter User Name : ");
						//search user
						if (admin.userSearch(user_name)) {
							ps.printData("");
							
							user_id = sc.getIntInput("Enter User Id : ");
							// issued a book
							if (admin.issuedBookADD(admin_id, user_id, book_id)) {
								int sum = quantity - 1;
								if (admin.bookQuantityUpdate(book_id, sum)) {
									ps.printData("Book Issued Successfully");
								}
							}
						} else {
							ps.printData("User Is Not Found ");
						}
					} else {
						ps.printData("Book Is Not Available ");
					}
				} else {
					ps.printData("Book Is Not Found");
				}

				adminIssueABookedSwitch();
				break;
			case 2:
				user_name = sc.getStringInput("Enter User Name : ");
				if (admin.userSearch(user_name)) {
					ps.printData("");
					
					user_id = sc.getIntInput("Enter User Id : ");
					book_name = sc.getStringInput("Enter Book Name : ");
					// searching book
					if (admin.bookSearch(book_name)) {
						ps.printData("");
						
						book_id = sc.getIntInput("Enter Book Id : ");
						int quantity = admin.getBookQuantity(book_id);
						String return_status = "done";
						// updating return status
						if (admin.issuedUserIdUpdate(user_id, book_id, return_status)) {
							int sum = quantity + 1;
							// updating book quantity
							if (admin.bookQuantityUpdate(book_id, sum)) {
								ps.printData("Book Returned Successfully");
							}
						}
					} else {
						ps.printData("Book Not Found");
					}

				} else {
					ps.printData("User Not Found");
				}

				adminIssueABookedSwitch();
				break;
			case 3:
				book_name = sc.getStringInput("Enter Book Name : ");
				if(admin.bookSearch(book_name)) {
					ps.printData("");
					
					book_id = sc.getIntInput("Enter Book Id : ");
					if(admin.issuedBookIdSearch(book_id)) {
						ps.printData("");
						
					}else {
						ps.printData("Issued Book Not Found");
					}
				} else {
					ps.printData("Book Not Found");
				}
				
				adminIssueABookedSwitch();
				break;
			case 4:
				user_name = sc.getStringInput("Enter User Name : ");
				if(admin.userSearch(user_name)) {
					ps.printData("");
					
					user_id = sc.getIntInput("Enter User Id : ");
					if(admin.issuedUserIdSearch(user_id)) {
						ps.printData("");
						
					}else {
						ps.printData("Issued Book Not Found");
					}
				}else {
					ps.printData("User Not Found");
				}
				
				adminIssueABookedSwitch();
				break;
			case 5:
				admin.issuedView();
				ps.printData("");
				
				adminIssueABookedSwitch();
				break;
			case 6:
				clearConsole.clearConsole();
				AdminHome welcomeAdmin = new AdminHome();
				welcomeAdmin.adminHomeSwitch();
				break;
			case 7:
				clearConsole.clearConsole();
				clearConsole.exitConsole();
				break;

			default:
				clearConsole.clearConsole();
				ps.printOptionErr();
				adminIssueABookedSwitch();
				break;
			}
		} catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			adminIssueABookedSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			adminIssueABookedSwitch();
		}

	}
}
