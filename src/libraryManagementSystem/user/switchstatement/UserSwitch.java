package libraryManagementSystem.user.switchstatement;

import java.text.ParseException;
import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.LoginSwitch;
import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.admin.switchstatement.AdminHome;
import libraryManagementSystem.admin.switchstatement.AdminSwitch;
import libraryManagementSystem.user.User;
import libraryManagementSystem.utils.ClearConsole;
import libraryManagementSystem.utils.CrytoGraphy;
import libraryManagementSystem.utils.FileReadAndWrite;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;
import libraryManagementSystem.validation.Validation;

public class UserSwitch {
	static Logger log = null;
	PrintStatement ps = null;
	ClearConsole clearConsole = null;
	ScannerInput sc = null;
	int user_id=0;
	FileReadAndWrite fileReadAndWrite=null;
	String searchBook=null;
	User user=null;
	Validation validation =null;
	CrytoGraphy crytoGraphy=null;
	
	public UserSwitch() {
		log = LogManager.getLogger(AdminSwitch.class.getName());
		sc = new ScannerInput();
		ps = new PrintStatement();
		fileReadAndWrite =new FileReadAndWrite();
		
		user=new User();
		validation =new Validation();
		crytoGraphy=new CrytoGraphy();
	}
	
	
	
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
			ps.printData(" 3) Change Password ");
			ps.printData(" 4) Back");
			ps.printData(" 5) Exit");

			// get Input From User
			int adminOption = ps.printIntOption();

			switch (adminOption) {
			case 1:
				 user_id=Integer.parseInt(FileReadAndWrite.userReadId());
				if(user.userIssuedUserIdSearch(user_id)) {
					ps.printData("");
				}else {
					ps.printData("");
					ps.printData("User is not Issued Book ");
				}
			
				userSwitch();
				break;
			case 2:
				searchBook=sc.getStringInput("Enter Book Name : ");
				if(user.userBookSearch(searchBook)) {
					ps.printData("");
				}else {
					ps.printData("");
					ps.printData("Book Not Found ");
				}
				
				userSwitch();
				break;
			case 3:
				user_id=Integer.parseInt(FileReadAndWrite.userReadId());
				String password =sc.getStringInput("Enter New Password : ");
				String cPassword=sc.getStringInput("Enter Confirm Password : ");
				if(validation.matchPassword(password, cPassword)) {
					String encodedPassword=crytoGraphy.setEncrpytedData(password);
					if(user.userChangePassword(user_id, encodedPassword)) {
						ps.printData("Password is Changed Successfully");
					}
				}else {
					ps.printData("Password And Confirm Password Is not Matching ");
				}
				userSwitch();
				break;
			case 4:
				clearConsole.clearConsole();
				LoginSwitch loginSwitch = new LoginSwitch();
				loginSwitch.adminWelcomeLoginSwitch();
				break;
			case 5:
				clearConsole.clearConsole();
				clearConsole.exitConsole();
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
