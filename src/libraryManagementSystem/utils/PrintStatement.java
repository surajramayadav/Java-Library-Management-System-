package libraryManagementSystem.utils;

public class PrintStatement {
	ScannerInput sc = new ScannerInput();
	
	public void printData(String msg) {
		System.out.println(msg);
	}
	public void printExit() {
		printData("Process is ending ...");
	}
	public void printOptionErr() {
		printData("Wrong Option !!!");
	}

	public void printOptionDesign() {
		printData("---------------------------------------------------------------------------------------");
		printData("Wrong Option !!!");
		printData("---------------------------------------------------------------------------------------");
	}
	
	public int printIntOption() {
		printData("---------------------------------------------------------------------------------------");
		int rootOption = sc.getIntInput("Enter an option : ");
		printData("---------------------------------------------------------------------------------------");
		return rootOption;
	}
	
	public String printStringOption() {
		printData("---------------------------------------------------------------------------------------");
		String rootOption = sc.getStringInput("Enter an option : ");
		printData("---------------------------------------------------------------------------------------");
		return rootOption;
	}
	
	public void printDataWithoutLN(String msg) {
		System.out.print(msg);
	}
	


}
