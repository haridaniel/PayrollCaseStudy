package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events;

public class AddedEmployeeEvent implements EmployeeChangedEvent {
	public int employeeId;
	public String name;

	public AddedEmployeeEvent(int employeeId, String name) {
		this.employeeId = employeeId;
		this.name = name;
	}
	
}
