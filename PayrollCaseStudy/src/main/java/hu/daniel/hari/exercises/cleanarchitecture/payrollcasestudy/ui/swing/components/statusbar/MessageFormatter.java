package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.components.statusbar;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.events.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.events.DeletedEmployeeEvent;

public class MessageFormatter {

	public String format(AddedEmployeeEvent event) {
		return "Added new employee: " + event.name + " with id " + event.employeeId;
	}

	public String format(DeletedEmployeeEvent event) {
		return "Deleted employee:(" + event.employeeId + ") " + event.name + " (this can't be undone!)  ";
	} 
	
}
