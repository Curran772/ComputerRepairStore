package Controllers;

import Objects.Product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Products {
	 // @XmlElement specifies XML element name for each object in the List
	   @XmlElement(name="product") 
	   private List<Product> products = new ArrayList<>(); // stores Products

	   // returns the List<Product>
	   public List<Product> getProducts() {return products;}
	}

