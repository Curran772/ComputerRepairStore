package Controllers;

public class Product {
	private String item;
	private int quantity;
	private double amount;
	
	public Product(String item, double amount, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
		
		if(quantity <= 0) {
			System.out.println("Quantity must be greater than 0");
		}else {
			this.quantity = quantity;
			this.amount = amount * quantity;
		}
	}

	public Product() {}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		if(quantity <= 0) {
			System.out.println("Quantity must be greater than 0");
		}else {
			this.quantity = quantity;
			this.amount = amount * quantity;
		}
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return String.format("%s", getItem());
	}
	
}
