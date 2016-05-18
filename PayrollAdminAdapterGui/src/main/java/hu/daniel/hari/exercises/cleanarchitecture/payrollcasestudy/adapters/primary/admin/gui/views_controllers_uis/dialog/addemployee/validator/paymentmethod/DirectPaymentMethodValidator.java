package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.validator.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.AbstractFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel.DirectPaymentMethod;

public class DirectPaymentMethodValidator extends AbstractFieldsValidator<DirectPaymentMethod> {

	@Override
	protected void addErrors(DirectPaymentMethod model) {
		if(model.accountNumber.isEmpty())
			addFieldValidatorError("accountNumber", Type.EMPTY_STRING);
	}
	
}
