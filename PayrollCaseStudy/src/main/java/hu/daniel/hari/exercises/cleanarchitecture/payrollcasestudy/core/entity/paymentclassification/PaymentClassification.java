package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

public abstract class PaymentClassification {

	public abstract int calculateAmount(DateInterval payInterval);

	public static interface PaymentClassificationFactory {
		SalariedPaymentClassification salariedPaymentClassification(int monthlySalary);
		HourlyPaymentClassification hourlyPaymentClassification(int hourlyWage);
		CommissionedPaymentClassification commissionedPaymentClassification(int biWeeklyBaseSalary, double commissionRate);
	}

}
