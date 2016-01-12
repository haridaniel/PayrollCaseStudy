package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype;

public class HourlyPaymentTypeResponse extends PaymentTypeResponse {
	public int hourlyWage;

	public HourlyPaymentTypeResponse(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	@Override
	public <T> T accept(PaymentTypeResponseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
