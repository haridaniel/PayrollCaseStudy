package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee;

import java.util.Optional;

public class AddHourlyEmployeeRequest extends AddEmployeeRequest {
	public int hourlyWage;
	
	public AddHourlyEmployeeRequest() {
	}
	public AddHourlyEmployeeRequest(Optional<Integer> employeeId, String name, String address, int hourlyWage) {
		super(employeeId, name, address);
		this.hourlyWage = hourlyWage;
	}
}