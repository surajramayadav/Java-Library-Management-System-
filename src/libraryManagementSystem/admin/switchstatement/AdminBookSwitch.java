package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminBookSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
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
				ps.printData("1 st");
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
}
