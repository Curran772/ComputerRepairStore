package Objects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Employees {
	 // @XmlElement specifies XML element name for each object in the List
	   @XmlElement(name="employee") 
	   private List<Employee> employees = new ArrayList<>(); // stores Employees

	   // returns the List<Employee>
	   public List<Employee> getEmployees() {return employees;}
	}

