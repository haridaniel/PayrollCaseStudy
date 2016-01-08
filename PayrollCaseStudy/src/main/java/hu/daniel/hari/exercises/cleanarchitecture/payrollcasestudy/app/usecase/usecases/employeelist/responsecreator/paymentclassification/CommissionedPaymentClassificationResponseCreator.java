package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentClassificationResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentClassificationType;

public class CommissionedPaymentClassificationResponseCreator extends PaymentClassificationResponseCreator<CommissionedPaymentClassification> {

	public CommissionedPaymentClassificationResponseCreator(CommissionedPaymentClassification paymentClassification) {
		super(paymentClassification);
	}

	@Override
	public PaymentClassificationType getType() {
		return PaymentClassificationType.COMMISSIONED;
	}

	@Override
	public String getFormattedType() {
		return String.format("%d / 2wk + %.0f%% sales", 
				paymentClassification.getBiWeeklyBaseSalary(), 
				paymentClassification.getCommissionRate() * 100
				);
	}

}
