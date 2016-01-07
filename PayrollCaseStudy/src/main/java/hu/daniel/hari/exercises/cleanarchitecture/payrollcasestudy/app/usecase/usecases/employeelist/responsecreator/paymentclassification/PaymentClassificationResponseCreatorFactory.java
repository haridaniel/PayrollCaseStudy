package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentClassificationResponseCreator;

public class PaymentClassificationResponseCreatorFactory {
	
	@SuppressWarnings("unchecked")
	public <T extends PaymentClassification> PaymentClassificationResponseCreator<T> create(T paymentClassification) {
		if (paymentClassification instanceof SalariedPaymentClassification)
			return (PaymentClassificationResponseCreator<T>) new SalariedPaymentClassificationResponseCreator((SalariedPaymentClassification) paymentClassification);
		else if (paymentClassification instanceof HourlyPaymentClassification)
			return (PaymentClassificationResponseCreator<T>) new HourlyPaymentClassificationResponseCreator((HourlyPaymentClassification) paymentClassification);
		else if (paymentClassification instanceof CommissionedPaymentClassification)
			return (PaymentClassificationResponseCreator<T>) new CommissionedPaymentClassificationResponseCreator((CommissionedPaymentClassification) paymentClassification);
		else
			throw new IllegalArgumentException();
	}
}