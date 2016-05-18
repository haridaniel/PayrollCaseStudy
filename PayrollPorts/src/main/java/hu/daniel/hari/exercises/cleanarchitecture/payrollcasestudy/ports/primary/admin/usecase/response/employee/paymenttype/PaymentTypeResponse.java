package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.PaymentType.PaymentTypeVisitor;

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
