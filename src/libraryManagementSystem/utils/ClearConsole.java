package libraryManagementSystem.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.LoginSwitch;
import libraryManagementSystem.admin.switchstatement.AdminHome;

public class ClearConsole {
	static Logger log = null;

	public void clearConsole() {
		log = LogManager.getLogger(ClearConsole.class.getName());
		try {
//			for (int i = 0; i < 50; ++i)
//				System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}

	}

	public void exitConsole() {
		LoginSwitch loginSwitch = new LoginSwitch();
		loginSwitch.adminWelcomeLoginSwitch();

	}
}
