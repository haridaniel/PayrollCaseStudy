package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.LocalDate;

public abstract class PaymentClassification {

	public abstract int calculatePay(LocalDate date);

}
