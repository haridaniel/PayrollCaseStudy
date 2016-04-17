package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee;

import java.util.Optional;

public class AddCommissionedEmployeeRequest extends AddEmployeeRequest {
	public int biWeeklyBaseSalary;
	public double commissionRate;

	public AddCommissionedEmployeeRequest() {
	}
	public AddCommissionedEmployeeRequest(Optional<Integer> employeeId, String name, String address,
			int biWeeklyBaseSalary, double commissionRate) {
		super(employeeId, name, address);
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}
}