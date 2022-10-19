import javafx.event.ActionEvent;

public abstract class Calculations extends Buttons{
	protected double enterNumber;
	protected abstract double numberButtonPressed(ActionEvent event);
	protected double pmtAmountField;
}
