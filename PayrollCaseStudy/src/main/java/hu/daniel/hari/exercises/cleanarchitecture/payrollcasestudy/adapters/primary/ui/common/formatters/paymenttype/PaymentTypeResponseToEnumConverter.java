package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.paymenttype;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeResponseVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.SalariedPaymentTypeResponse;

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
