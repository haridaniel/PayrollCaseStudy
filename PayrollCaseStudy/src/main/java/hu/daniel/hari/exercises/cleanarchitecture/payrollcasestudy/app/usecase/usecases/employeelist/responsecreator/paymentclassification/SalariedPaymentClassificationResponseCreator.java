package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentClassificationResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentClassificationType;

public class SalariedPaymentClassificationResponseCreator extends PaymentClassificationResponseCreator<SalariedPaymentClassification> {

	public SalariedPaymentClassificationResponseCreator(SalariedPaymentClassification paymentClassification) {
		super(paymentClassification);
	}

	@Override
	public PaymentClassificationType getType() {
		return PaymentClassificationType.SALARIED;
	}

	@Override
	public String getFormattedType() {
		return String.format("%d / month", 
				paymentClassification.getMonthlySalary()
				);
	}

}
