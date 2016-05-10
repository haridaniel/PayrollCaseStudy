package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import javax.swing.JFormattedTextField;

public class IntegerField extends JFormattedTextField {

	public IntegerField() {
		super(NumberFormat.getIntegerInstance());
	}

	public Optional<Integer> getInteger() {
		return getValue() == null? Optional.empty() : Optional.of(Integer.parseInt(getValue().toString()));
	}
	
	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public Object getValue() {
		return super.getValue();
	}
	
	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}
}
