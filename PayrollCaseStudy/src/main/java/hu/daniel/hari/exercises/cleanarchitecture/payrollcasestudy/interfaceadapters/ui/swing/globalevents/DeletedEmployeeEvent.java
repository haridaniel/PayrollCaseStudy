package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents;

public class DeletedEmployeeEvent implements EmployeeCountChangedEvent {
	public int employeeId;
	public String name;

	public DeletedEmployeeEvent(int employeeId, String name) {
		this.employeeId = employeeId;
		this.name = name;
	}
	
}
