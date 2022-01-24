package libraryManagementSystem.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import libraryManagementSystem.admin.Admin;
import libraryManagementSystem.database.DatabaseHelper;

public class FileReadAndWrite {
	static Logger log = null;

	public FileReadAndWrite() {
		try {
			log = LogManager.getLogger(Admin.class.getName());

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	public void adminWriteId(String admin_id) {

		try {

			BufferedWriter bw = new BufferedWriter(
					new FileWriter("/media/suraj/HardDisk/eclipse/libraryManagementSystem/loginInfo/adminLoginId.txt"));
			bw.write(admin_id);
			bw.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static String adminReadId() {
		String getAdminId = null;
		try {
			BufferedReader br = new BufferedReader(
					new FileReader("/media/suraj/HardDisk/eclipse/libraryManagementSystem/loginInfo/adminLoginId.txt"));
			String getAllFileData;
			while ((getAllFileData = br.readLine()) != null) {
				getAdminId = getAllFileData;
			}
			br.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return getAdminId;

	}
	
	public void userWriteId(String user_id) {

		try {

			BufferedWriter bw = new BufferedWriter(
					new FileWriter("/media/suraj/HardDisk/eclipse/libraryManagementSystem/loginInfo/userLoginId.txt"));
			bw.write(user_id);
			bw.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static String userReadId() {
		String getUserId = null;
		try {
			BufferedReader br = new BufferedReader(
					new FileReader("/media/suraj/HardDisk/eclipse/libraryManagementSystem/loginInfo/userLoginId.txt"));
			String getAllFileData;
			while ((getAllFileData = br.readLine()) != null) {
				getUserId = getAllFileData;
			}
			br.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return getUserId;

	}

}
