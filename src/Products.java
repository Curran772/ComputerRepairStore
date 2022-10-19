import java.math.BigDecimal;

public class Products {

	private String item;
	private int quantity;
	private double amount;
	
	public Products(String item, int quantity, double amount) {
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
	
	
	
}
