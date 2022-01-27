package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminUserSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	Admin admin = null;
	String user_name = null;
	String user_phone = null;
	String user_address = null;
	int user_id = 0;
	ScannerInput sc = null;

	public AdminUserSwitch() {
		admin = new Admin();
		log = LogManager.getLogger(AdminSwitch.class.getName());
		sc = new ScannerInput();
		ps = new PrintStatement();
	}

	public void adminUserSwitch() {
		try {
			log = LogManager.getLogger(AdminUserSwitch.class.getName());
			clearConsole = new ClearConsole();
			ScannerInput sc = new ScannerInput();
			ps = new PrintStatement();
//			clearConsole.clearConsole();
			ps.printData("User");
			ps.printData("1) Search");
			ps.printData("2) Add");
			ps.printData("3) View");
			ps.printData("4) Change User Phone Number");
			ps.printData("5) Delete");
			ps.printData("6) Back");
			ps.printData("7) Exit");

			int adminOption = ps.printIntOption();
			switch (adminOption) {
			case 1:
				user_name = sc.getStringInput("Enter User Name : ");
				if (admin.userSearch(user_name)) {
					ps.printData("");
					
				} else {
					ps.printData("User Not Found");
				}

				adminUserSwitch();
				break;
			case 2:
				user_name = sc.getStringInput("Enter User Name : ");
				user_phone = sc.getStringInput("Enter User Phone Number : ");
				user_address = sc.getStringInput("Enter User Address : ");
				if (admin.userAdd(user_name, user_phone, user_address)) {
					ps.printData("User Added Successfully");

				}
				adminUserSwitch();

				break;
			case 3:
				admin.userView();
				ps.printData("");
				
				adminUserSwitch();
				break;
			case 4:
				user_name = sc.getStringInput("Enter User Name : ");
				if (admin.userSearch(user_name)) {
					ps.printData("");
					user_id = sc.getIntInput("Enter User Id : ");
					user_phone = sc.getStringInput("Enter User Phone Number : ");
					if (admin.userPhoneUpdate(user_id, user_phone)) {
						ps.printData("User Updatd Successfully");

					}
				} else {
					ps.printData("User Not Found");
				}
				adminUserSwitch();

				break;
			case 5:
				user_name = sc.getStringInput("Enter User Name : ");
				if (admin.userSearch(user_name)) {
					ps.printData("");
					user_id = sc.getIntInput("Enter User Id : ");
					if(admin.issuedBookDeleteUser(user_id)) {
						if (admin.userDelete(user_id)) {
							ps.printData("User Deleted Successfully");
						}
					}
					
				} else {
					ps.printData("User Not Found");
				}
				adminUserSwitch();

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
				adminUserSwitch();
				break;
			}
		} catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			adminUserSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			adminUserSwitch();
		}
	}
}
