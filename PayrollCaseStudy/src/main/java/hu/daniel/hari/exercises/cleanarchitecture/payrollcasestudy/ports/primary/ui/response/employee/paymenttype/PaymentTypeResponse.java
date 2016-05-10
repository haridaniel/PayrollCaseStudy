package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.employee.paymenttype;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType.PaymentTypeVisitor;

public abstract class PaymentTypeResponse {
	
	public abstract <T> T accept(PaymentTypeResponseVisitor<T> visitor);

	public interface PaymentTypeResponseVisitor<T> {
		T visit(SalariedPaymentTypeResponse salariedPaymentTypeResponse);
		T visit(HourlyPaymentTypeResponse hourlyPaymentTypeResponse);
		T visit(CommissionedPaymentTypeResponse commissionedPaymentTypeResponse);
	}
	
	public static class PaymentTypeResponseFactory implements PaymentTypeVisitor<PaymentTypeResponse>{

		@Override
		public PaymentTypeResponse visit(CommissionedPaymentType commissionedPaymentType) {
			return new CommissionedPaymentTypeResponse(commissionedPaymentType.getBiWeeklyBaseSalary(), commissionedPaymentType.getCommissionRate());
		}

		@Override
		public PaymentTypeResponse visit(SalariedPaymentType salariedPaymentType) {
			return new SalariedPaymentTypeResponse(salariedPaymentType.getMonthlySalary());
		}

		@Override
		public PaymentTypeResponse visit(HourlyPaymentType hourlyPaymentType) {
			return new HourlyPaymentTypeResponse(hourlyPaymentType.getHourlyWage());
		}

	}
}
