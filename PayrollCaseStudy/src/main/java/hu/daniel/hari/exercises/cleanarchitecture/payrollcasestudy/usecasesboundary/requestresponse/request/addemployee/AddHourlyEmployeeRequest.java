package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee;

public class AddHourlyEmployeeRequest extends AddEmployeeRequest {
	public int hourlyWage;

	public AddHourlyEmployeeRequest(int employeeId, String name, String address, int hourlyWage) {
		super(employeeId, name, address);
		this.hourlyWage = hourlyWage;
	}
}