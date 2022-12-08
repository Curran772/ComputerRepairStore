package Objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;


public class Invoice {
		   // @XmlElement specifies XML element name for each object in the List
		   @XmlElement(name="invoice") 
		   private List<Invoice> invoice = new ArrayList<>(); // stores Invoices

		   // returns the List<Invoice>
		   public List<Invoice> getInvoice() {return invoice;}
		
			
}


