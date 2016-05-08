package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.controller.msg;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.common.SmartDateFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AffiliationChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.PaymentsFulfilledEvent;

public class EventMessageFormatter {
	private SmartDateFormatter smartDateFormatter = new SmartDateFormatter();
	
	public String format(AddedEmployeeEvent event) {
		return String.format("Added new employee: %s with id %s", event.name, event.employeeId);
	}

	public String format(DeletedEmployeeEvent event) {
		return String.format("Deleted employee:(%s) %s (this can't be undone!)", event.employeeId, event.name);
	}

	public String format(AddedTimeCardEvent event) {
		return String.format("Timecard of %s has been added to %s", event.date, event.employeeName);
	}

	public String format(PaymentsFulfilledEvent event) {
		return String.format("Payments has been fulfilled for %s employee for pay-day %s as a total net of %s.", 
				event.employeeCount, smartDateFormatter.format(event.payDate), event.totalNetAmount);
	}

	public String format(AffiliationChangedEvent event) {
		return changesHasBeenSaved();
	}

	private String changesHasBeenSaved() {
		return String.format("Saved");
	} 
	
}
