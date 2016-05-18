package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.controller.msg;

public class ConfirmMessageFormatter {
	
	public String deleteEmployee(String employeeName) {
		return String.format("You are about to delete employee %s. Are you sure?", employeeName);
	}

	public String fulfillPayments(int employeeCount) {
		return String.format("Fullfill payments for the %s employee?", employeeCount);
	}

	public String timeCardAlreadyExists() {
		return String.format("Timecard already exists for such date. Update?");
	}
	
}
