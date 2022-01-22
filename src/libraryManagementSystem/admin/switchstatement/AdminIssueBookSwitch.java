package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminIssueBookSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;

	public void adminIssueABookedSwitch() {

		try {
			log = LogManager.getLogger(AdminIssueBookSwitch.class.getName());
			clearConsole = new ClearConsole();
			ScannerInput sc = new ScannerInput();
			ps = new PrintStatement();
//			clearConsole.clearConsole();
			ps.printData("Issue Book");
			ps.printData("1) Issue a Book");
			ps.printData("2) Update Issued Book");
			ps.printData("3) Search Issued Book");
			ps.printData("4) View Issued Book ");
			ps.printData("5) Back");
			ps.printData("6) Exit");

			int adminOption = ps.printIntOption();
			switch (adminOption) {
			case 1:
				ps.printData("1st");
				break;
			case 2:
				ps.printData("1 st");
				break;
			case 3:
				ps.printData("1 st");
				break;
			case 4:
				ps.printData("1 st");
				break;

			case 5:
				clearConsole.clearConsole();
				AdminHome welcomeAdmin = new AdminHome();
				welcomeAdmin.adminHomeSwitch();
				break;
			case 6:
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
