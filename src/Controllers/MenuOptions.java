package Controllers;

public enum MenuOptions {
	   // declare contents of enum type
	   EMPLOYEE_ID(1),
	   EMPLOYEE_LAST_NAME(2),
	   EMPLOYEE_FIRST_NAME(3),
	   END(4);

	   private final int value; // current menu option

	   // constructor
	   private MenuOptions(int value) {this.value = value;}
	} 
