package Controllers;

import javafx.beans.property.*;

public class Product {
	private String item;
	private int quantity;
	private double amount;

	private StringProperty item_name;
	private DoubleProperty item_amount;
	private DoubleProperty item_qty;
	
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

	public Product() {
		this.item_name = new SimpleStringProperty();
		this.item_amount = new SimpleDoubleProperty();
		this.item_qty = new SimpleDoubleProperty();
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

	public StringProperty prodNameProperty() {
		return this.item_name;
	}

	public void setProdName(String name) { this.item_name.set(name); }

	public DoubleProperty prodAmountProperty() { return this.item_amount; }

	public void setProdQty(Double qty) { this.item_qty.set(qty); }

	public DoubleProperty prodQtyProperty() { return this.item_qty; }

	public void setProdAmount(Double amount) { this.item_amount.set(amount); }

	@Override
	public String toString() {
		return String.format("%s", getItem());
	}
	
}
