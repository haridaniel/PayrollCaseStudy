package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype;

public abstract class PaymentTypeResponse {
	
	public abstract <T> T accept(PaymentTypeResponseVisitor<T> visitor);

	public interface PaymentTypeResponseVisitor<T> {
		T visit(SalariedPaymentTypeResponse salariedPaymentTypeResponse);
		T visit(HourlyPaymentTypeResponse hourlyPaymentTypeResponse);
		T visit(CommissionedPaymentTypeResponse commissionedPaymentTypeResponse);
	}
}
