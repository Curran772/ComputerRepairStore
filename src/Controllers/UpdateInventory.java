package Controllers;


public class UpdateInventory {
	private String item;
	private double amount;
	private int quantity;
	
	
	public UpdateInventory(String item, double amount, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
	}
	// initializes an Account with default values
	public UpdateInventory() {
		this(" ", 0.0, 0);
		}

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
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
