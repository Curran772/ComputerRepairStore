package Controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import Objects.*;

import javax.xml.bind.JAXB;

public class CreateEmployeeSequentialFile {

	public static void main(String[] args) {
		try (BufferedWriter output = Files.newBufferedWriter(Paths.get("employees.xml"))) {

			Scanner input = new Scanner(System.in);

			// stores the Emloyee before XML serialization
			Employees employees = new Employees();

			System.out.printf("%s%n%s%n? ", 
					"Enter Employee ID, first name, last name, username and password.",
					"Enter end-of-file indicator to end input.");

			while (input.hasNext()) { // loop until end-of-file indicator
				try {
					// create new record
					Employee record = new Employee(input.next(), input.next(), input.next(), input.next(),
							input.nextLine());

					// add to EmployeeList
					employees.getEmployees().add(record);
				} catch (NoSuchElementException elementException) {
					System.err.println("Invalid input. Please try again.");
					input.nextLine(); // discard input so user can try again
				}

				System.out.print("? ");
			}

			// write AccountList's XML to output
			JAXB.marshal(employees, output);
			
		} 
		catch (IOException ioException) {
			System.err.println("Error opening file. Terminating.");
		}
	}
	

}
