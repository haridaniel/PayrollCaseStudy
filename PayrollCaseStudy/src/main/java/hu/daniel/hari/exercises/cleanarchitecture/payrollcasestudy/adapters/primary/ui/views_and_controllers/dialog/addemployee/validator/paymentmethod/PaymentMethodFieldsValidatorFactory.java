package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.paymentmethod;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymasterPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymentMethod.PaymentMethodVisitor;

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
