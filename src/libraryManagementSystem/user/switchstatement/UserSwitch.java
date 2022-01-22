package libraryManagementSystem.user.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.LoginSwitch;
import libraryManagementSystem.admin.switchstatement.AdminHome;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class UserSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;

	public void userSwitch() {
		try {
			clearConsole = new ClearConsole();
			log = LogManager.getLogger(UserSwitch.class.getName());
			// User Input Class
			ScannerInput sc = new ScannerInput();
			// Contains print Message
			ps = new PrintStatement();
			// After Admin Loggin Screen
			AdminHome wa = new AdminHome();
			
			ps.printData("Welcome Library management system");
			ps.printData(" 1) View Issued Book ");
			ps.printData(" 2) Search Book ");
			ps.printData(" 3) Edit Information ");
			ps.printData(" 4) Change Password ");
			ps.printData(" 5) Exit");

			// get Input From User
			int adminOption = ps.printIntOption();

			switch (adminOption) {
			case 1:
				// Checking Username And password for authenication
				ps.printData("View Issued Book");

				break;
			case 2:
				ps.printData("Search Book");
				break;
			case 3:
				// Checking Username And password for authenication
				ps.printData("Edit Information");

				break;
			case 4:
				// Checking Username And password for authenication
				ps.printData("Change Password");

				break;
			case 5:
				clearConsole.clearConsole();
				ps.printExit();
				System.exit(0);
				break;
			// Default case statement
			default:
				clearConsole.clearConsole();
				ps.printOptionErr();
				userSwitch();
				break;
			}

		} catch (InputMismatchException ex) {
			// TODO: handle exception

			ps.printData("Wrong Option is entered");
			userSwitch();
		} catch (Exception e) {

			log.error(e.getMessage());
			userSwitch();
			// TODO: handle exception
		}
	}
}
