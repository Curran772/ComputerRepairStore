package Objects;

import javax.xml.bind.annotation.XmlType;



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

	public void update(UpdateInventory ui) {
		// Check if the product quantity and updateInventory quantity are equal
		if (getQuantity() != ui.getQuantity()) {
			setQuantity(ui.getQuantity());
		}
		// Check if the product amount and updateInventory amount are equal
		if (getAmount() != ui.getAmount()) {
			setAmount(ui.getAmount());
		}
		}
		
	public Product(String item, double amount, int quantity) {
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Product() {
		this("", 0, 0);
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
	
