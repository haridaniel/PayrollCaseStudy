package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee;

public class AddSalariedEmployeeRequest extends AddEmployeeRequest {
	public int monthlySalary;

	public AddSalariedEmployeeRequest(int employeeId, String name, String address, int monthlySalary) {
		super(employeeId, name, address);
		this.monthlySalary = monthlySalary;
	}
}