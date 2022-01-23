package libraryManagementSystem.admin;

public interface Book extends Genre{

	default void test3() {
		System.out.print("book");
	}
}
