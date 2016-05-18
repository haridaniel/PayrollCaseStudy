package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.SalariedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeResponseVisitor;

public class PaymentTypeResponseToEnumConverter implements PaymentTypeResponseVisitor<PaymentType> {

	@Override
	public PaymentType visit(SalariedPaymentTypeResponse salariedPaymentTypeResponse) {
		return PaymentType.SALARIED;
	}

	@Override
	public PaymentType visit(HourlyPaymentTypeResponse hourlyPaymentTypeResponse) {
		return PaymentType.HOURLY;
	}

	@Override
	public PaymentType visit(CommissionedPaymentTypeResponse commissionedPaymentTypeResponse) {
		return PaymentType.COMMISSIONED;
	}

}
