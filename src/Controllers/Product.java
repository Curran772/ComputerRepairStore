package Controllers;

public class Product {

	// Class variables
	private String item;
	private int quantity;
	private double amount;
	private String thumbImage;

	public Product(String item, double amount, int quantity, String thumbImage) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
		this.thumbImage = thumbImage;

		if (quantity <= 0) {
			System.out.println("Quantity must be greater than 0");
		} else {
			this.quantity = quantity;
		}
	}

	public Product(String item, double amount, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;

		if (quantity <= 0) {
			System.out.println("Quantity must be greater than 0");
		} else {
			this.quantity = quantity;
		}
	}

    // Product variable setters
	public void setItem(String item) {
		this.item = item;
	}
	public void setQuantity(int quantity) {
		if (quantity <= 0) {
			System.out.println("Quantity must be greater than 0");
		} else {
			this.quantity = quantity;
		}
	}
	public void setAmount(double amount) {
		if (amount < 0) {
			System.out.println("Price must be greater than or equal to zero!");
		} else {
			this.amount = amount;
		}
	}

	// Product variable getters
	public String getItem() { return item; }
	public int getQuantity() { return quantity; }
	public double getAmount() { return amount; }
	public String getThumbImage() { return thumbImage; }

	@Override
	public String toString() {
		return String.format("%s\t\t%d\t\t%.02f", getItem(), getQuantity(), getAmount());
	}
}
