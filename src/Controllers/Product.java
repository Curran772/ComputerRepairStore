package Controllers;

public class Product {

	// Class variables
	private String item;
	private int quantity;
	private double amount;
	private String thumbImage;

	public Product(String item, double amount, int quantity, String thumbImage) {
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
		this.thumbImage = thumbImage;
	}

	public Product(String item, double amount, int quantity) {
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
	}

    // Product variable setters
	public void setItem(String item) {
		this.item = item;
	}
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public void setAmount(double amount) { this.amount = amount; }

	// Product variable getters
	public String getItem() { return item; }
	public int getQuantity() { return quantity; }
	public double getAmount() { return amount; }
	public String getThumbImage() { return thumbImage; }

	@Override
	public String toString() {
		return String.format("%-40s%-10d%-10.2f\n", getItem(), getQuantity(), getAmount());
	}
}
	
