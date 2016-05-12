package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.paymenttype.SalariedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeResponseVisitor;

public class PaymentTypeResponseToStringFormatter implements PaymentTypeResponseVisitor<String> {

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
