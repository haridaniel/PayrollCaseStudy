package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;

public class DateField extends JTextField {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate value;
	
	public void setValue(LocalDate localDate) {
		this.value = localDate;  
		super.setText(formatter.format(localDate));
	}
	
	public LocalDate getValue() {
		return value;
	}
	
	
}
