package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.visitortest;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType.PaymentTypeVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;

public class PayClassFormatter implements PaymentTypeVisitor<String> {

	@Override
	public String visit(CommissionedPaymentType paymentType) {
		return String.format("%d / 2wk + %.0f%% sales", 
				paymentType.getBiWeeklyBaseSalary(), 
				paymentType.getCommissionRate() * 100
				);
	}

	@Override
	public String visit(SalariedPaymentType paymentType) {
		return String.format("%d / month", 
				paymentType.getMonthlySalary()
				);	
	}

	@Override
	public String visit(HourlyPaymentType paymentType) {
		return String.format("%d / hour", 
				paymentType.getHourlyWage()
				);
	}

}
