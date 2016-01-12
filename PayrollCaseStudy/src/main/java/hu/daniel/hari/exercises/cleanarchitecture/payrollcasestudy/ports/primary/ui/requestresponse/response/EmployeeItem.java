package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response;

public class EmployeeItem {
	public int id;
	public String name;
	public String address;

	public PaymentTypeEnum paymentTypeEnum;
	
	public enum PaymentTypeEnum {
		SALARIED,
		HOURLY,
		COMMISSIONED
	}
	
}