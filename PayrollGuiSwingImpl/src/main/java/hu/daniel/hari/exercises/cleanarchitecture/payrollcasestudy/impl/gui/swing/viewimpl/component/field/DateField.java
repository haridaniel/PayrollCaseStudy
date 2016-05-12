package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JFormattedTextField;

public class DateField extends JFormattedTextField {

	public DateField(String dateFormat) {
		super(new SimpleDateFormat(dateFormat));
	}
	
	public LocalDate getDate() {
		Date date = (Date) getValue();
		return date == null? null : new java.sql.Date(date.getTime()).toLocalDate();
	}

	public void setDate(LocalDate date) {
		setValue(date == null? null : java.sql.Date.valueOf(date));
	}
	
	/**
	 * @deprecated use getDate()
	 */
	@Deprecated
	@Override
	public Object getValue() {
		return super.getValue();
	}
	
	/**
	 * @deprecated use setDate()
	 */
	@Deprecated
	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}
}
