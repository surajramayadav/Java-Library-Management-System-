package libraryManagementSystem.utils;

import java.io.Console;
import java.util.Scanner;

public class ScannerInput {

	public int getIntInput(String msg) {
		System.out.print(msg);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public String getStringInput(String msg) {
		System.out.print(msg);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public String getPassword(String msg) {
		String getPassword = null;
		Console console=System.console();
		if (console != null) {
			char[] password = console.readPassword("msg");
			getPassword = new String(password);
		} else {
			System.out.println("Console Not Found");
		}
		return getPassword;
	}

}
