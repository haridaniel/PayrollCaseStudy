package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentTypeResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentTypeEnum;

public class CommissionedPaymentTypeResponseCreator extends PaymentTypeResponseCreator<CommissionedPaymentType> {

	public CommissionedPaymentTypeResponseCreator(CommissionedPaymentType paymentType) {
		super(paymentType);
	}

	@Override
	public PaymentTypeEnum getType() {
		return PaymentTypeEnum.COMMISSIONED;
	}

	@Override
	public String getFormattedType() {
		return String.format("%d / 2wk + %.0f%% sales", 
				paymentType.getBiWeeklyBaseSalary(), 
				paymentType.getCommissionRate() * 100
				);
	}

}
