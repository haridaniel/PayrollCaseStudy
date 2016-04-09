package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.dev;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JFormattedTextField;

public class DateField extends JFormattedTextField {

	public DateField(DateFormat dateFormat) {
		super(dateFormat);
	}
	
	public LocalDate getDate() {
		Date date = (Date) getValue();
		return date == null? null : new java.sql.Date(date.getTime()).toLocalDate();
	}

	public void setDate(LocalDate date) {
		setValue(date == null? null : java.sql.Date.valueOf(date));
	}

	@Deprecated
	@Override
	public Object getValue() {
		return super.getValue();
	}
	
	@Deprecated
	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}
}
