package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.paymenttype;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeResponseVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.SalariedPaymentTypeResponse;

public class WagingFormatter implements PaymentTypeResponseVisitor<String> {

	@Override
	public String visit(HourlyPaymentTypeResponse paymentType) {
		return String.format("%d / hour", 
				paymentType.hourlyWage
				);
	}

	@Override
	public String visit(SalariedPaymentTypeResponse paymentType) {
		return String.format("%d / month", 
				paymentType.monthlySalary
				);	
	}

	@Override
	public String visit(CommissionedPaymentTypeResponse paymentType) {
		return String.format("%d / 2wk + %.0f%% sales", 
				paymentType.biWeeklyBaseSalary, 
				paymentType.commissionRate * 100
				);
	}


}
