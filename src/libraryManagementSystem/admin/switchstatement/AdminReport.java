package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.NotANumberException;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminReport {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;

	
	public void AdminReportSwitch() {
		try {
			log = LogManager.getLogger(AdminUserSwitch.class.getName());
			clearConsole = new ClearConsole();
			ScannerInput sc = new ScannerInput();
			ps = new PrintStatement();
			Admin admin=new Admin();
//			clearConsole.clearConsole();
			ps.printData("Report");
			ps.printData("1) Number Of Books Return By Today");
			ps.printData("2) Count Of Books By Genre");
			ps.printData("3) Back");
			ps.printData("4) Exit");

			int adminOption = ps.printIntOption();
			switch (adminOption) {
			case 1:
				
				if (admin.returnBookToday()) {
					ps.printData("");	
				}else {
					ps.printData("");
					ps.printData("No Books Return By Today");
				}

				AdminReportSwitch();
				break;
			case 2:
				if(admin.countBookByGenre()) {
					ps.printData("");
				}else {
					ps.printData("No Genre");
				}
				AdminReportSwitch();				
				break;
			
			case 3:
				clearConsole.clearConsole();
				AdminHome welcomeAdmin = new AdminHome();
				welcomeAdmin.adminHomeSwitch();
				break;
			case 4:
				clearConsole.clearConsole();
				clearConsole.exitConsole();
				break;

			default:
				clearConsole.clearConsole();
				ps.printOptionErr();
				AdminReportSwitch();
				break;
			}
		}catch ( NotANumberException na) {
			// TODO: handle exception
			clearConsole.clearConsole();
			AdminReportSwitch();
		} 
 
		catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			AdminReportSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			AdminReportSwitch();
		}
	
		
	}
}
