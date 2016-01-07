package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe.employeemanager.table;

import java.util.Optional;

public class EmployeesTableSelectionChangedEvent {
	public Optional<Integer> employeeId;
	
	public EmployeesTableSelectionChangedEvent(Optional<Integer> employeeId) {
		this.employeeId = employeeId;
	} 
	
}
