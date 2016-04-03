package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.util.Optional;

public class EmployeeListSelectionChangedEvent {
	public Optional<Integer> employeeId;
	
	public EmployeeListSelectionChangedEvent(Optional<Integer> employeeId) {
		this.employeeId = employeeId;
	} 
	
}
