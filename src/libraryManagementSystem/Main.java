package libraryManagementSystem;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.admin.switchstatement.AdminHome;
import libraryManagementSystem.database.DatabaseHelper;
import libraryManagementSystem.utils.PrintStatement;
import libraryManagementSystem.utils.ScannerInput;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

public class Main {
	static Connection connection = null;
	static Logger log = null;

	public static void main(String[] args) {
		try {	
			log = LogManager.getLogger(Main.class.getName());
			LoginSwitch adminLogin = new LoginSwitch();
			adminLogin.adminWelcomeLoginSwitch();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
