package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe.statusbar.messageformatter;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.globalevents.DeletedEmployeeEvent;

public class StatusBarMessageFormatter {

	public String format(AddedEmployeeEvent event) {
		return "Added new employee: " + event.name + " with id " + event.employeeId;
	}

	public String format(DeletedEmployeeEvent event) {
		return "Deleted employee:(" + event.employeeId + ") " + event.name + " (this can't be undone!)  ";
	} 
	
}
