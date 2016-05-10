package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.addemployee;

public class AddHourlyEmployeeRequest extends AddEmployeeRequest {
	public int hourlyWage;
	
	public AddHourlyEmployeeRequest() {
	}
	public AddHourlyEmployeeRequest(Integer employeeId, String name, String address, int hourlyWage) {
		super(employeeId, name, address);
		this.hourlyWage = hourlyWage;
	}
}