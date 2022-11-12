package Controllers;

public class Employee {
	
	private String employeeID;
	private String fName;
	private String lName;
	

	public Employee(String employeeID, String fName, String lName) {
				
		this.employeeID = employeeID;
		this.fName = fName;
		this.lName = lName;
		
	}
	//getter methods
	public String getEmployeeID() {
		return employeeID;
	}
	
	public String getFirstName() {
		return fName;
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
		
	public String toString() {
		String s = String.format("%s %s%n\tEmployee #%s", 
				getFirstName(), getLastName(), getEmployeeID());		
		return s;
	}
}
