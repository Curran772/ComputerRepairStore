import javafx.event.ActionEvent;

public class NumberButtons extends Calculations{
	private double button7;

	public NumberButtons(double button7) {
		super();
		this.button7 = button7;
		
		if (enterNumber == 0) {
			 pmtAmountField = this.button7; 
			 } else { 
			enterNumber = pmtAmountField + button7;
			 }
	}

	@Override
	protected double numberButtonPressed(ActionEvent event) {
		button7 = 7;
		return button7;
	}

	public double getButton7() {
		return button7;
	}

	public void setButton7(double button7) {
		if (enterNumber == 0) {
			 pmtAmountField = this.button7; 
			 } else { 
			enterNumber = pmtAmountField + button7;
			 }
	}

	@Override
	public String toString() {
		return String.format("%.2f", button7);
	}
	

}
