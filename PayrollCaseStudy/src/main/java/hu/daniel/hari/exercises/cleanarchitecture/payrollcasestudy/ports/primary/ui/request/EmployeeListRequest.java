package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request;

import java.time.LocalDate;

public class EmployeeListRequest implements Request {
	public final LocalDate currentDate;
	
	public EmployeeListRequest(LocalDate currentDate) {
		this.currentDate = currentDate;
	}
}
