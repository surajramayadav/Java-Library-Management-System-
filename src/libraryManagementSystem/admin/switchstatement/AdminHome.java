package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.LoginSwitch;
import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.FileReadAndWrite;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminHome {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	int admin_id = 0;
	boolean superAdmin = false;


	public void adminHomeSwitch() {

		try {
			log = LogManager.getLogger(AdminHome.class.getName());
			clearConsole = new ClearConsole();
			ScannerInput sc = new ScannerInput();
			ps = new PrintStatement();
			FileReadAndWrite fileReadAndWrite = new FileReadAndWrite();
			admin_id = Integer.parseInt(fileReadAndWrite.adminReadId());
			Admin admin = new Admin();
			String result = admin.getSuperAdminInfo(admin_id);
			if (result.equals("false")) {
				ps.printData("SomeThing Went Wrong ");
			} else {
				if (result.equals("super")) {
					superAdmin = true;
				} else {
					superAdmin = false;
				}
			}
			// checking admin is normal or super
			if(superAdmin) {
				ps.printData("Welcome Admin");
				ps.printData("1) Issue Book");
				ps.printData("2) User");
				ps.printData("3) Book");
				ps.printData("4) Admin ");
				ps.printData("5) Report");
				ps.printData("6) Back");
				ps.printData("7) Exit");
			}else {
				ps.printData("Welcome Admin");
				ps.printData("1) Issue Book");
				ps.printData("2) User");
				ps.printData("3) Book");
				ps.printData("4) Report");
				ps.printData("5) Back");
				ps.printData("6) Exit");
			}
//			clearConsole.clearConsole();
			

			
			int adminOption = ps.printIntOption();
			if(superAdmin) {
				switch (adminOption) {
				case 1:
//					clearConsole.clearConsole();
					AdminIssueBookSwitch issueBook = new AdminIssueBookSwitch();
					clearConsole.clearConsole();
					issueBook.adminIssueABookedSwitch();
					break;
				case 2:
//					clearConsole.clearConsole();
					AdminUserSwitch userSwitch = new AdminUserSwitch();
					clearConsole.clearConsole();
					userSwitch.adminUserSwitch();
					break;
				case 3:
//					clearConsole.clearConsole();
					AdminBookSwitch bookAdminSwitch = new AdminBookSwitch();
					clearConsole.clearConsole();
					bookAdminSwitch.adminBookSwitch();
					break;
				case 4:
//					clearConsole.clearConsole();
					AdminSwitch adminSwitch = new AdminSwitch();
					clearConsole.clearConsole();
					adminSwitch.adminSwitch();
					break;
				case 5:
					clearConsole.clearConsole();
					AdminReport adminReport=new AdminReport();
					adminReport.AdminReportSwitch();
					break;
				case 6:
					clearConsole.clearConsole();
					LoginSwitch adminLogin = new LoginSwitch();
					clearConsole.clearConsole();
					adminLogin.adminWelcomeLoginSwitch();
					break;
				case 7:
					clearConsole.clearConsole();
					clearConsole.exitConsole();
					break;

				default:
					clearConsole.clearConsole();
					ps.printOptionErr();
					adminHomeSwitch();
					break;
				}
			}else {
				switch (adminOption) {
				case 1:
//					clearConsole.clearConsole();
					AdminIssueBookSwitch issueBook = new AdminIssueBookSwitch();
					clearConsole.clearConsole();
					issueBook.adminIssueABookedSwitch();
					break;
				case 2:
//					clearConsole.clearConsole();
					AdminUserSwitch userSwitch = new AdminUserSwitch();
					clearConsole.clearConsole();
					userSwitch.adminUserSwitch();
					break;
				case 3:
//					clearConsole.clearConsole();
					AdminBookSwitch bookAdminSwitch = new AdminBookSwitch();
					clearConsole.clearConsole();
					bookAdminSwitch.adminBookSwitch();
					break;
				case 4:
					clearConsole.clearConsole();
					AdminReport adminReport=new AdminReport();
					adminReport.AdminReportSwitch();
					break;
				case 5:
					clearConsole.clearConsole();
					LoginSwitch adminLogin = new LoginSwitch();
					clearConsole.clearConsole();
					adminLogin.adminWelcomeLoginSwitch();
					break;
				case 6:
					clearConsole.clearConsole();
					clearConsole.exitConsole();
					break;

				default:
					clearConsole.clearConsole();
					ps.printOptionErr();
					adminHomeSwitch();
					break;
				}
			}
			
		} catch (InputMismatchException ex) {
			// TODO: handle exception
//			clearConsole.clearConsole();
			ps.printData("Wrong Option is entered");
			adminHomeSwitch();
		} catch (Exception e) {
//			clearConsole.clearConsole();
			// TODO: handle exception
			log.error(e);
			adminHomeSwitch();
		}

	}
}
