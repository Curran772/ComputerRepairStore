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
         System.out.printf("%-10s%-12s%-12s%n", "Employee ID",
            "First Name", "Last Name");

         for (Employee employee : employees.getEmployees()) {
            System.out.printf("%-10d%-12s%-12s%10.2f%n",  
               employee.getEmployeeID(), employee.getFirstName(), 
               employee.getLastName(), employee.getEmployeePassword());
         }
      } 
      catch (IOException ioException) {
         System.err.println("Error opening file.");
      } 
   }
}
