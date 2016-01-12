package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentTypeResponseCreator;

public class PaymentTypeResponseCreatorFactory {
	
	@SuppressWarnings("unchecked")
	public <T extends PaymentType> PaymentTypeResponseCreator<T> create(T paymentType) {
		if (paymentType instanceof SalariedPaymentType)
			return (PaymentTypeResponseCreator<T>) new SalariedPaymentTypeResponseCreator((SalariedPaymentType) paymentType);
		else if (paymentType instanceof HourlyPaymentType)
			return (PaymentTypeResponseCreator<T>) new HourlyPaymentTypeResponseCreator((HourlyPaymentType) paymentType);
		else if (paymentType instanceof CommissionedPaymentType)
			return (PaymentTypeResponseCreator<T>) new CommissionedPaymentTypeResponseCreator((CommissionedPaymentType) paymentType);
		else
			throw new IllegalArgumentException();
	}
}