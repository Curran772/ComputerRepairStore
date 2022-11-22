package Controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.xml.bind.JAXB;

public class SequentialFile {

	public static void main(String[] args) {
		/*
		 * try(BufferedWriter output =
		 * Files.newBufferedWriter(Paths.get("invoice.xml"))) {
		 * 
		 * Scanner input = new Scanner(System.in);
		 * 
		 * // stores the Invoice before XML serialization Invoice invoice = new
		 * Invoice();
		 * 
		 * System.out.printf("%s%n%s%n? ",
		 * "Enter account number, first name, last name and balance.",
		 * "Enter end-of-file indicator to end input.");
		 * 
		 * while (input.hasNext()) { // loop until end-of-file indicator try { // create
		 * new record Product record = new Product(input.nextInt(), input.next(),
		 * input.next(), input.nextDouble());
		 * 
		 * // add to InvoiceList invoice.getInvoice().add(record); } catch
		 * (NoSuchElementException elementException) {
		 * System.err.println("Invalid input. Please try again."); input.nextLine(); //
		 * discard input so user can try again }
		 * 
		 * System.out.print("? "); }
		 * 
		 * // write AccountList's XML to output JAXB.marshal(invoice, output); } catch
		 * (IOException ioException) {
		 * System.err.println("Error opening file. Terminating."); }
		 */
	}
}
