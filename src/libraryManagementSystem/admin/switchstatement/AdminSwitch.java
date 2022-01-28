package libraryManagementSystem.admin.switchstatement;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.CrytoGraphy;
import libraryManagementSystem.utils.NotANumberException;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class AdminSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	Admin admin = null;
	String admin_username = null;
	String admin_password = null;
	String admin_role = null;
	int admin_id = 0;
	ScannerInput sc = null;
	CrytoGraphy crytoGraphy=null;

	public AdminSwitch() {
		admin = new Admin();
		log = LogManager.getLogger(AdminSwitch.class.getName());
		sc = new ScannerInput();
		ps = new PrintStatement();
		crytoGraphy =new CrytoGraphy();
	}

	public void adminSwitch() {
		try {

			clearConsole = new ClearConsole();

			ps.printData("Admin");
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
				admin_username = sc.getStringInput("Enter Admin UserName : ");
				if (admin.adminSearch(admin_username)) {
					ps.printData("");
				
				} else {
					ps.printData("Admin Not Found");
				}

				adminSwitch();
				break;
			case 2:
				admin_username = sc.getStringInput("Enter Admin UserName : ");
				String password = sc.getStringInput("Enter Admin Password : ");
				admin_password=crytoGraphy.setEncrpytedData(password);
				admin_role = sc.getStringInput("Enter Admin Role (super/normal) : ");
				if (admin.adminAdd(admin_username, admin_password, admin_role)) {
					ps.printData("Admin Added Successfully");
				}
				adminSwitch();

				break;
			case 3:
				admin.adminView();
				
				adminSwitch();
				break;
			case 4:
				adminUpdateSwitch();
				break;
			case 5:
				admin_username = sc.getStringInput("Enter Admin UserName : ");
				if (admin.adminSearch(admin_username)) {
					ps.printData("");
					
					admin_id = sc.getIntInput("Enter Admin User Id : ");
					if (admin.issuedBookDeleteAdmin(admin_id)) {
						if (admin.adminDelete(admin_id)) {
							ps.printData("Admin Deleted Successfully");
						}
					}

				} else {
					ps.printData("Admin Not Found");
				}

				adminSwitch();

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
				adminSwitch();
				break;
			}
		}catch ( NotANumberException na) {
			// TODO: handle exception
			clearConsole.clearConsole();
			adminSwitch();
		} 
		 
		catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			adminSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			adminSwitch();
		}
	}

	public void adminUpdateSwitch() {

		try {
			ps.printData("Admin Update");
			ps.printData("1) Change Username");
			ps.printData("2) Change Password");
			ps.printData("3) Change Role");
			ps.printData("4) Back");
			ps.printData("5) Exit");

			int adminOption = ps.printIntOption();
			switch (adminOption) {
			case 1:
				admin_username = sc.getStringInput("Enter Admin Current UserName : ");
				admin.adminSearch(admin_username);
				ps.printData("");
				admin_id = sc.getIntInput("Enter Admin Id : ");
				admin_username = sc.getStringInput("Enter Admin New UserName : ");
				if (admin.adminUserNameUpdate(admin_id, admin_username)) {
					ps.printData("Admin UserName Updated Successfully");
				} else {
					ps.printData("Something Went Wrong !!!");
				}

				adminUpdateSwitch();
				break;
			case 2:
				admin_username = sc.getStringInput("Enter Admin Current UserName : ");
				admin.adminSearch(admin_username);
				ps.printData("");
				admin_id = sc.getIntInput("Enter Admin Id : ");
				admin_password = sc.getStringInput("Enter Admin New Password : ");
				if (admin.adminPasswordUpdate(admin_id, admin_password)) {
					ps.printData("Admin Password Updated Successfully");
				} else {
					ps.printData("Something Went Wrong !!!");
				}

				adminUpdateSwitch();
				break;
			case 3:
				admin_username = sc.getStringInput("Enter Admin Current UserName : ");
				admin.adminSearch(admin_username);
				ps.printData("");
				admin_id = sc.getIntInput("Enter Admin Id : ");
				admin_role = sc.getStringInput("Enter Admin New Role : ");
				if (admin.adminRoleUpdate(admin_id, admin_role)) {
					ps.printData("Admin Role Updated Successfully");
				} else {
					ps.printData("Something Went Wrong !!!");
				}

				adminUpdateSwitch();
				break;

			case 4:
				clearConsole.clearConsole();
				adminSwitch();
				break;
			case 5:
				clearConsole.clearConsole();
				clearConsole.exitConsole();
				break;

			default:
				clearConsole.clearConsole();
				ps.printOptionErr();
				adminUpdateSwitch();
				break;
			}
		}
		catch ( NotANumberException na) {
			// TODO: handle exception
			clearConsole.clearConsole();
			adminUpdateSwitch();
		} 
		 catch (InputMismatchException ex) {
			// TODO: handle exception
			ps.printData("Wrong Option is entered");
			adminUpdateSwitch();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			adminUpdateSwitch();
		}

	}
}
