package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.Request;

public class AddEmployeeRequest implements Request {
	public Integer employeeId;
	public String name;
	public String address;

	public AddEmployeeRequest() {
	}
	public AddEmployeeRequest(Integer employeeId, String name, String address) {
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
}