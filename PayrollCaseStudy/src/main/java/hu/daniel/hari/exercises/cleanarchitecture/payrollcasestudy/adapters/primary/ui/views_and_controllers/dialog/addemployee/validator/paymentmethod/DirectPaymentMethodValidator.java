package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.AbstractFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.DirectPaymentMethod;

public class DirectPaymentMethodValidator extends AbstractFieldsValidator {

	public DirectPaymentMethodValidator(DirectPaymentMethod paymentMethod) {
		if(paymentMethod.accountNumber.isEmpty()) {
			addFieldValidatorError("accountNumber", Type.EMPTY_STRING);
		}
	}
	
}
