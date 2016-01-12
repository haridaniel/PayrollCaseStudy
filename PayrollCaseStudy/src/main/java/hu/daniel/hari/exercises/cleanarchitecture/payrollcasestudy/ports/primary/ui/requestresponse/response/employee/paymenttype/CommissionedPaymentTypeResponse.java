package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype;

public class CommissionedPaymentTypeResponse extends PaymentTypeResponse {
	public int biWeeklyBaseSalary;
	public double commissionRate;

	public CommissionedPaymentTypeResponse(int biWeeklyBaseSalary, double commissionRate) {
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}
	
	@Override
	public <T> T accept(PaymentTypeResponseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
