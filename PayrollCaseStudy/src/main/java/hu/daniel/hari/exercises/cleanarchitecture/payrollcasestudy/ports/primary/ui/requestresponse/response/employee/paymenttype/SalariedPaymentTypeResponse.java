package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype;

public class SalariedPaymentTypeResponse extends PaymentTypeResponse {
	public int monthlySalary;

	public SalariedPaymentTypeResponse(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public <T> T accept(PaymentTypeResponseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
