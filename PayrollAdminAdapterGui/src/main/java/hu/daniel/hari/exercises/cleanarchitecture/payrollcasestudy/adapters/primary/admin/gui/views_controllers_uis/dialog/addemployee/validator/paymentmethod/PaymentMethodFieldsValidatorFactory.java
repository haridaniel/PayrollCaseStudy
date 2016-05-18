package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.validator.paymentmethod;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymasterPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymentMethod.PaymentMethodVisitor;

public class PaymentMethodFieldsValidatorFactory implements PaymentMethodVisitor<List<FieldValidatorError>> {
	
	@Override
	public List<FieldValidatorError> visit(PaymasterPaymentMethod paymentMethod) {
		return new PaymasterPaymentMethodValidator().getErrors(paymentMethod);
	}

	@Override
	public List<FieldValidatorError> visit(DirectPaymentMethod paymentMethod) {
		return new DirectPaymentMethodValidator().getErrors(paymentMethod);
	}
	
}
