package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee;

import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;

public class AddEmployeeRequest implements Request {
	public Optional<Integer> employeeId;
	public String name;
	public String address;

	public AddEmployeeRequest() {
	}
	public AddEmployeeRequest(Optional<Integer> employeeId, String name, String address) {
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
}