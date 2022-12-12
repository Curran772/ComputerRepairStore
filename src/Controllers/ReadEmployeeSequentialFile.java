package Controllers;

import java.io.BufferedReader;      
import java.io.IOException;      
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.bind.JAXB;

public class ReadEmployeeSequentialFile {
	 public static void main(String[] args) {
	      // try to open file for deserialization
	      try(BufferedReader input = 
	         Files.newBufferedReader(Paths.get("employees.xml"))) {
	         // unmarshal the file's contents
	         Employees employees = JAXB.unmarshal(input, Employees.class);
	         
	         // display contents
	         System.out.printf("%-20s%-20s%-20s%10s%10s%n", "EmployeeID",
	            "First Name", "Last Name", "Username", "Password");

	         for (Employee employee : employees.getEmployees()) {
	            System.out.printf("%-20s%-20s%-20s%10s%10s%n",  
	               employee.getEmployeeID(), employee.getFName(), employee.getLName(), employee.getUsername(), employee.getPassword());
	         }
	      } 
	      catch (IOException ioException) {
	         System.err.println("Error opening file.");
	      } 
	   }
	}