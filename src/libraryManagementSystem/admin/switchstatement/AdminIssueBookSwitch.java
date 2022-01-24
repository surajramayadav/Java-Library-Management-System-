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
	String book_name= null;
	String user_name= null;
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
				admin.bookSearch(book_name);
				ps.printData("");
				book_id=sc.getIntInput("Enter Book Id : ");
				user_name = sc.getStringInput("Enter User Name : ");
				admin.userSearch(user_name);
				ps.printData("");
				user_id=sc.getIntInput("Enter User Id : ");
				if(admin.issuedBookADD(admin_id, user_id, book_id)) {
					ps.printData("Book Issued Successfully");
				} else {
					ps.printData("Something Went Wrong !!!");
				}
				adminIssueABookedSwitch();
				break;
			case 2:
				
				user_name = sc.getStringInput("Enter User Name : ");
				admin.userSearch(user_name);
				ps.printData("");
				user_id=sc.getIntInput("Enter User Id : ");
				book_name = sc.getStringInput("Enter Book Name : ");
				admin.bookSearch(book_name);
				ps.printData("");
				book_id=sc.getIntInput("Enter Book Id : ");
				String return_status= "done";
				if(admin.issuedUserIdUpdate(user_id,book_id,return_status)) {
					ps.printData("Book Returned Successfully");
				} else {
					ps.printData("Something Went Wrong !!!");
				}
				adminIssueABookedSwitch();
				break;
			case 3:
				book_name = sc.getStringInput("Enter Book Name : ");
				admin.bookSearch(book_name);
				ps.printData("");
				book_id=sc.getIntInput("Enter Book Id : ");
				admin.issuedBookIdSearch(book_id);
				adminIssueABookedSwitch();
				break;
			case 4:
				user_name = sc.getStringInput("Enter User Name : ");
				admin.userSearch(user_name);
				ps.printData("");
				user_id=sc.getIntInput("Enter User Id : ");
				admin.issuedUserIdSearch(user_id);
				adminIssueABookedSwitch();
				break;
			case 5:
				admin.issuedView();
				adminIssueABookedSwitch();
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
