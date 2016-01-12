package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentTypeResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentTypeEnum;

public class SalariedPaymentTypeResponseCreator extends PaymentTypeResponseCreator<SalariedPaymentType> {

	public SalariedPaymentTypeResponseCreator(SalariedPaymentType paymentType) {
		super(paymentType);
	}

	@Override
	public PaymentTypeEnum getType() {
		return PaymentTypeEnum.SALARIED;
	}

	@Override
	public String getFormattedType() {
		return String.format("%d / month", 
				paymentType.getMonthlySalary()
				);
	}

}
