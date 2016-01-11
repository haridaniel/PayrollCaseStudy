package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.visitortest;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification.PaymentClassificationVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;

public class PayClassFormatter implements PaymentClassificationVisitor<String> {

	@Override
	public String visit(CommissionedPaymentClassification paymentClassification) {
		return String.format("%d / 2wk + %.0f%% sales", 
				paymentClassification.getBiWeeklyBaseSalary(), 
				paymentClassification.getCommissionRate() * 100
				);
	}

	@Override
	public String visit(SalariedPaymentClassification paymentClassification) {
		return String.format("%d / month", 
				paymentClassification.getMonthlySalary()
				);	
	}

	@Override
	public String visit(HourlyPaymentClassification paymentClassification) {
		return String.format("%d / hour", 
				paymentClassification.getHourlyWage()
				);
	}

}
