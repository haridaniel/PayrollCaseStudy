package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

public class EmployeeItem {
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