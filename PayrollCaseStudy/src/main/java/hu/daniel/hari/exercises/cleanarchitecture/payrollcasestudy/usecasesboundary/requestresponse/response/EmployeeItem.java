package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response;

public class EmployeeItem {
	public int id;
	public String name;
	public String address;

	public EmployeeItem.PaymentClassificationType paymentClassificationType;
	
	public enum PaymentClassificationType {
		SALARIED,
		HOURLY,
		COMMISSIONED
	}
	
}