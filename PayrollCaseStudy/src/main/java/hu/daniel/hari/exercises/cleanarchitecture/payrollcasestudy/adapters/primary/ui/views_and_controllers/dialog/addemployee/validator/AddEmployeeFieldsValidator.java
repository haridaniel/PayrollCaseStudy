package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.AbstractFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.paymentmethod.PaymentMethodFieldsValidatorFactorz;

public abstract class AddEmployeeFieldsValidator<T extends EmployeeViewModel> extends AbstractFieldsValidator {
	private T model;
	
	public AddEmployeeFieldsValidator(T model) {
		this.model = model;
		validate();
	}

	private void validate() {
		collectBaseErrors();
		collectPaymentMethodErrors();
		collectSubTypeErrors(model);
	}

	private void collectBaseErrors() {
		if(!model.employeeId.isPresent()) {
			addFieldValidatorError("id", Type.REQUIRED);
		}
		if(model.name.isEmpty())
			addFieldValidatorError("name", Type.EMPTY_STRING);
	}
	
	private void collectPaymentMethodErrors() {
		addFieldValidatorErrors(model.paymentMethod.accept(new PaymentMethodFieldsValidatorFactorz()));
	}

	protected abstract void collectSubTypeErrors(T model);
	
}