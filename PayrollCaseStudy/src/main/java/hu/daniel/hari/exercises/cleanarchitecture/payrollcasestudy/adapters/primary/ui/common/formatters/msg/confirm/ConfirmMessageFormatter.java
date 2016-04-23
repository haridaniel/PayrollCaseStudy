package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.confirm;

public class ConfirmMessageFormatter {
	
	public String deleteEmployee(String employeeName) {
		return String.format("You are about to delete employee %s. Are you sure?", employeeName);
	}
	
}
