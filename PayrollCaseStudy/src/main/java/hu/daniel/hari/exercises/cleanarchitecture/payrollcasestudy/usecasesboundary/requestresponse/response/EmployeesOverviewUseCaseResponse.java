package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response;

import java.util.List;

public class EmployeesOverviewUseCaseResponse implements Response {

	public List<EmployeeItem> employeeItems;
	
	public static class EmployeeItem {
		public int id;
		public String name;
		public String address;

		public PaymentClassificationType paymentClassificationType;
		
		public enum PaymentClassificationType {
			SALARIED,
			HOURLY,
			COMMISSIONED
		}
		
	}
	
}
