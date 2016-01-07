package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;

public class AddEmployeeRequest implements Request {
	public int employeeId;
	public String name;
	public String address;

	public AddEmployeeRequest(int employeeId, String name, String address) {
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
}