package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.messageformatter;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;

public class StatusBarMessageFormatter {

	public String format(AddedEmployeeEvent event) {
		return String.format("Added new employee: %s with id %s", event.name, event.employeeId);
	}

	public String format(DeletedEmployeeEvent event) {
		return String.format("Deleted employee:(%s) %s (this can't be undone!)", event.employeeId, event.name);
	}

	public String format(AddedTimeCardEvent event) {
		return String.format("Timecard of %s has been added to %s", event.date, event.employeeName);
	} 
	
}
