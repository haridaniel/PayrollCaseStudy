package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class PaymentClassification {

	public abstract int calculateAmount(DateInterval payInterval);

	public abstract <T> T accept(PaymentClassificationVisitor<T> visitor);
	
	public static interface PaymentClassificationFactory {
		SalariedPaymentClassification salariedPaymentClassification(int monthlySalary);
		HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage);
		CommissionedPaymentClassification commissionedPaymentClassification(int biWeeklyBaseSalary, double commissionRate);
	}

	public interface PaymentClassificationVisitor<T> {
		public T visit(CommissionedPaymentClassification commissionedPaymentClassification);
		public T visit(SalariedPaymentClassification salariedPaymentClassification);
		public T visit(HourlyPaymentClassification hourlyPaymentClassification);
	}
	
}
