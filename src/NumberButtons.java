import javafx.event.ActionEvent;

public class NumberButtons extends Calculations{
	private double button1;

	public NumberButtons(double button1) {
		super();
		this.button1 = button1;
		
		if (enterNumber == 0) {
			 pmtAmountField = this.button1; 
			 } else { 
			enterNumber = pmtAmountField + button1;
			 }
	}

	//@Override
	//protected double numberButtonPressed(ActionEvent event) {
		//button1 = 1;
		//return button1;
	//}

	public double getButton1() {
		return button1;
	}

	public void setButton1(double button1) {
		if (enterNumber == 0) {
			 pmtAmountField = this.button1; 
			 } else { 
			enterNumber = pmtAmountField + button1;
			 }
	}

	@Override
	public String toString() {
		return String.format("%.2f", button1);
	}
	

}
