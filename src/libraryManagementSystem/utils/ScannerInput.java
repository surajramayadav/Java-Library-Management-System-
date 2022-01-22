package libraryManagementSystem.utils;

import java.util.Scanner;

public class ScannerInput {

	public int getIntInput(String msg) {
		System.out.print(msg);
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		return  sc.nextInt();  
	}
	
	public String getStringInput(String msg) {
		System.out.print(msg);
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		return  sc.nextLine();  
	}
	
	
	
}
