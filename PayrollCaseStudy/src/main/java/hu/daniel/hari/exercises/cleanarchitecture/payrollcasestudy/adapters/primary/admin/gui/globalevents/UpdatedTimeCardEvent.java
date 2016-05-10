package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents;

import java.time.LocalDate;

public class UpdatedTimeCardEvent implements PersistentDataChangedEvent {
	public String employeeName;
	public LocalDate date;
	public UpdatedTimeCardEvent(String employeeName, LocalDate date) {
		this.employeeName = employeeName;
		this.date = date;
	}
	
}
