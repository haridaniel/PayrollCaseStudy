package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table;

import java.util.Optional;

public class EmployeesTableSelectionChangedEvent {
	public Optional<Integer> employeeId;
	
	public EmployeesTableSelectionChangedEvent(Optional<Integer> employeeId) {
		this.employeeId = employeeId;
	} 
	
}
