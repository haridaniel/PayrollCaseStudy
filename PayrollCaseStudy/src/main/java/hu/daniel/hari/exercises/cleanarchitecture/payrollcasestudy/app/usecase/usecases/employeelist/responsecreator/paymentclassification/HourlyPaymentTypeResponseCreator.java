package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.EmployeeListResponseCreator.PaymentTypeResponseCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentTypeEnum;

public class HourlyPaymentTypeResponseCreator extends PaymentTypeResponseCreator<HourlyPaymentType> {

	public HourlyPaymentTypeResponseCreator(HourlyPaymentType paymentType) {
		super(paymentType);
	}

	@Override
	public PaymentTypeEnum getType() {
		return PaymentTypeEnum.HOURLY;
	}

	@Override
	public String getFormattedType() {
		return String.format("%d / hour", 
				paymentType.getHourlyWage()
				);
	}

}
