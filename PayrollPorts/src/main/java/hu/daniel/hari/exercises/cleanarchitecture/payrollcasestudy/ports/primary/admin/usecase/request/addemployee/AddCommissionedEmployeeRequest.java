package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee;

public class AddCommissionedEmployeeRequest extends AddEmployeeRequest {
	public int biWeeklyBaseSalary;
	public double commissionRate;

	public AddCommissionedEmployeeRequest() {
	}
	public AddCommissionedEmployeeRequest(Integer employeeId, String name, String address,
			int biWeeklyBaseSalary, double commissionRate) {
		super(employeeId, name, address);
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}
}