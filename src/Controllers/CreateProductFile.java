package Controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.bind.JAXB;

public class CreateProductFile {
   public static void main(String[] args) {
      // open old.xml, write objects to it then close file
      try(BufferedWriter output = 
         Files.newBufferedWriter(Paths.get("products.xml"))) {
         
         Scanner input = new Scanner(System.in);

         // stores the Accounts before XML serialization
         Products products = new Products(); 

         System.out.printf("%s%n%s%n? ", 
            "Enter product name, price and quantity.",
            "Enter end-of-file indicator to end input.");

         while (input.hasNext()) { // loop until end-of-file indicator
            try {
               // create new record
               Product record = new Product(input.next(),
                  input.nextDouble(), input.nextInt());

               // add to AccountList
               products.getProducts().add(record);
            } 
            catch (NoSuchElementException elementException) {
               System.err.println("Invalid input. Please try again.");
               input.nextLine(); // discard input so user can try again
            } 

            System.out.print("? ");
         }

         // write AccountList's XML to output
         JAXB.marshal(products, output); 
      }
      catch (IOException ioException) {
         System.err.println("Error opening file. Terminating.");
      } 
   } 
} 
