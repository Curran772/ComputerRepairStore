package Controllers;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadInvoiceTextFile {
   public static void main(String[] args) {
      // open invoice.txt, read its contents and close the file
      try(Scanner input = new Scanner(Paths.get("invoice.txt"))) {
         //System.out.printf("%-12s%-12s%-12s%n", "EmployeeID",
           // "First Name", "Last Name");

         // read record from file
         while (input.hasNext()) { // while there is more to read
             //display record contents                     
            System.out.printf("%s%s%s%s%n", input.next(),input.next(),input.next(), input.next());          
         }       
      } 
      catch (IOException | NoSuchElementException | 
         IllegalStateException e) {
         e.printStackTrace();
      } 
   } 
} 
