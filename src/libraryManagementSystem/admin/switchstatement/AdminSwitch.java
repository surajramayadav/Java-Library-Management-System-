package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	Admin admin =null;
	public AdminSwitch() {
		 admin=new Admin();
	}
	
	public void adminSwitch() {
		try {
			log = LogManager.getLogger(AdminSwitch.class.getName());
			clearConsole = new ClearConsole();
			ScannerInput sc = new ScannerInput();
			ps = new PrintStatement();
//			clearConsole.clearConsole();
			ps.printData("Admin");
			ps.printData("1) Search");
			ps.printData("2) Add");
			ps.printData("3) View");
			ps.printData("4) Update");
			ps.printData("5) Delete");
			ps.printData("6) Back");
			ps.printData("7) Exit");

			int adminOption=ps.printIntOption();
			switch (adminOption) {
			case 1:
				ps.printData("1st");
				break;
			case 2:
				String admin_username =sc.getStringInput("Enter Admin User Name : ");
				String admin_password =sc.getStringInput("Enter Admin Password : ");
				String admin_role =sc.getStringInput("Enter Admin Role : ");
				if(admin.adminAdd(admin_username, admin_password, admin_role)) {
					ps.printData("Admin Added Successfully");
					adminSwitch();
				}else {
					ps.printData("Something Went Wrong !!!");
					adminSwitch();
				}
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
				ps.printOptionErr();
				adminSwitch();
				break;
			}
		} catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			adminSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			adminSwitch();
		}
	}
}
