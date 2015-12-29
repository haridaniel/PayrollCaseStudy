package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.table;

import java.util.Optional;

public class EmployeeSelectionChangedEvent {
	public Optional<Integer> employeeId;
	public EmployeeSelectionChangedEvent(Optional<Integer> employeeId) {
		this.employeeId = employeeId;
	} 
	
}
