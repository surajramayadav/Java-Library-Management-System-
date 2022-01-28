package libraryManagementSystem;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.utils.FileReadAndWrite;
import libraryManagementSystem.admin.switchstatement.AdminHome;
import libraryManagementSystem.user.User;
import libraryManagementSystem.user.switchstatement.UserSwitch;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.CrytoGraphy;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;

public class LoginSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	
	
	public void adminWelcomeLoginSwitch() {
		try {
			clearConsole = new ClearConsole();
			log = LogManager.getLogger(LoginSwitch.class.getName());
			// User Input Class
			ScannerInput sc = new ScannerInput();
			// Contains print Message
			ps = new PrintStatement();
			// After Admin Loggin Screen
			AdminHome wa = new AdminHome();
			// User Switch
			UserSwitch us=new UserSwitch();
			
//			clearConsole.clearConsole();
			ps.printData("Welcome Library management system");
			ps.printData(" 1) Admin Login ");
			ps.printData(" 2) User Login ");
			ps.printData(" 3) Exit");

			// get Input From User
			int adminOption = ps.printIntOption();

			switch (adminOption) {
			case 1:
				// Checking Username And password for authenication
				ps.printData("Admin Login");
				String adminUserName = sc.getStringInput("Enter Username : ");
				
				String adminPassword = sc.getStringInput("Enter Password : ");
				
//				ps.printData("UserName Is " + adminUserName + " Password is " + adminPassword);
				
				Admin admin=new Admin();
				// return  false or Admin Id
				String result = admin.adminLogin(adminUserName, adminPassword);
				// Checking What is returning
				if(result.equals("false")) {
					ps.printData("Username and Password is incorrect. please try again !!!");
					adminWelcomeLoginSwitch();
					
				}else {
					// clear Console
					clearConsole.clearConsole();
					// Write Id In File
					FileReadAndWrite fileReadAndWrite=new FileReadAndWrite();
					fileReadAndWrite.adminWriteId(result);
					// SuccessFully Login then navigate to admin dashbaord
					wa.adminHomeSwitch();
				}
				break;
			case 2:
//				clearConsole.clearConsole();
				ps.printData("User Login");
				String UserName = sc.getStringInput("Enter Phone Number : ");
				String Password = sc.getStringInput("Enter Password : ");
				User user=new User();
				//Checking User Login or not
				String user_id = user.userLogin(UserName, Password);
				if(user_id.equals("false")) {
					ps.printData("Username and Password is incorrect. please try again !!!");
					adminWelcomeLoginSwitch();
					
				}else {
					clearConsole.clearConsole();
					FileReadAndWrite fileReadAndWrite=new FileReadAndWrite();
					fileReadAndWrite.userWriteId(user_id);
					us.userSwitch();
				}
				UserSwitch userSwitch =new UserSwitch();
				clearConsole.clearConsole();
				userSwitch.userSwitch();
				break;
			case 3:
				clearConsole.clearConsole();
				ps.printExit();
				System.exit(0);
				break;
			// Default case statement
			default:
				ps.printOptionErr();
				adminWelcomeLoginSwitch();
				break;
			}

		} catch (InputMismatchException ex) {
			// TODO: handle exception
			clearConsole.clearConsole();
			ps.printData("Wrong Option is entered");
			adminWelcomeLoginSwitch();
		} catch (Exception e) {

			log.error(e.getMessage());
			adminWelcomeLoginSwitch();
			// TODO: handle exception
		}
	}
}
