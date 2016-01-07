package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentClassificationResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentClassificationType;

public class HourlyPaymentClassificationResponseCreator extends PaymentClassificationResponseCreator<HourlyPaymentClassification> {

	public HourlyPaymentClassificationResponseCreator(HourlyPaymentClassification paymentClassification) {
		super(paymentClassification);
	}

	@Override
	public PaymentClassificationType getType() {
		return PaymentClassificationType.HOURLY;
	}

	@Override
	public String getFormattedType() {
		return String.format("%d / hour", 
				paymentClassification.getHourlyWage()
				);
	}

}
