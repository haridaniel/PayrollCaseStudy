package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events;

import java.time.LocalDate;

public class AddedTimeCardEvent implements PersistentDataChangedEvent {
	public String employeeName;
	public LocalDate date;
	public AddedTimeCardEvent(String employeeName, LocalDate date) {
		this.employeeName = employeeName;
		this.date = date;
	}
	
}
