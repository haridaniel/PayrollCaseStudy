package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class PaymentType {

	public abstract int calculateAmount(DateInterval payInterval);

	public abstract <T> T accept(PaymentTypeVisitor<T> visitor);
	
	public static interface PaymentTypeFactory {
		SalariedPaymentType salariedPaymentType(int monthlySalary);
		HourlyPaymentType hourlyPaymentType(int hourlyWage);
		CommissionedPaymentType commissionedPaymentType(int biWeeklyBaseSalary, double commissionRate);
	}

	public interface PaymentTypeVisitor<T> {
		public T visit(CommissionedPaymentType commissionedPaymentType);
		public T visit(SalariedPaymentType salariedPaymentType);
		public T visit(HourlyPaymentType hourlyPaymentType);
	}
	
}
