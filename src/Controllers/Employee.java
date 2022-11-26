package Controllers;

public class Employee {
	
	private String employeeID;
	private String fName;
	private String lName;
	private String employeePassword;
	
	// initializes an Employee with default values
	public Employee() {this("", "", "", "");} 
	   
	public Employee(String employeeID, String fName, String lName, String employeePassword) {
				
		this.employeeID = employeeID;
		this.fName = fName;
		this.lName = lName;
		this.employeePassword = employeePassword;
		
	}
	//getter methods
	public String getEmployeeID() {
		return employeeID;
	}
	
	public String getFirstName() {
		return fName;
	}
	
	public String getEmployeePassword() {
		return employeePassword;
	}
	
	public String getLastName() {
		return lName;
	}
		
	//setter methods
	public void setEmployeeID(String employeeID) {
							
		if(employeeID.isEmpty()) {
			System.out.println("Must enter employee ID");
		}else {
			this.employeeID = employeeID;
		}
				
	}
	
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
		
	public String toString() {
		String s = String.format("%s %s%n\tEmployee #%s", 
				getFirstName(), getLastName(), getEmployeeID());		
		return s;
	}
}
