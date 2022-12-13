package Objects;

public class Employee {
	
	private String employeeID;
	private String fName;
	private String lName;	
	private String username;
	private String password;
	
	// initializes an Employee with default values
	public Employee() {this("", "", "", "", "");} 
	   
	public Employee(String employeeID, String fName, String lName, String username, String password) {
				
		this.employeeID = employeeID;
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		
	}
	
	
	//getter methods
	public String getEmployeeID() {
		return employeeID;
	}
	
	public String getFName() {
		return fName;
	}
		
	public String getLName() {
		return lName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	//setter methods
	public void setEmployeeID(String employeeID) {
							
		if(employeeID.isEmpty()) {
			System.out.println("Must enter employee ID");
		}else {
			this.employeeID = employeeID;
		}
				
	}

	public void setFName(String fName) { this.fName = fName; }
	public void setLName(String lName) { this.lName = lName; }

	public void setPassword(String password) {
		if (password.isEmpty()) {
			System.out.println("Must enter employee password");
		} else {
			this.password = "123";
		}
	}

	
	public void setUsername(String username) {
			this.username = employeeID;
	}
		
	public String toString() {
		return String.format("%s %s", getFName(), getLName());
	}
}
