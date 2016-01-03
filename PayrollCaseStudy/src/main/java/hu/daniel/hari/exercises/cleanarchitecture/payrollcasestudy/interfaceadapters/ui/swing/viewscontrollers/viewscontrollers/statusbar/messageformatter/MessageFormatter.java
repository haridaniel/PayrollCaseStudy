package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.messageformatter;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.DeletedEmployeeEvent;

public class MessageFormatter {

	public String format(AddedEmployeeEvent event) {
		return "Added new employee: " + event.name + " with id " + event.employeeId;
	}

	public String format(DeletedEmployeeEvent event) {
		return "Deleted employee:(" + event.employeeId + ") " + event.name + " (this can't be undone!)  ";
	} 
	
}
