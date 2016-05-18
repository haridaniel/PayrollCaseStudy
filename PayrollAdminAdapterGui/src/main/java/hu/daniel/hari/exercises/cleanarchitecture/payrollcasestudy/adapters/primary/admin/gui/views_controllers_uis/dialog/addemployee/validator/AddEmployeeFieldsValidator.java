package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.AbstractFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.validator.paymentmethod.PaymentMethodFieldsValidatorFactory;

public abstract class AddEmployeeFieldsValidator<T extends EmployeeViewModel> extends AbstractFieldsValidator<T> {
	
	@Override
	protected void addErrors(T model) {
		addBaseErrors(model);
		addPaymentMethodErrors(model);
		addSubTypeErrors(model);
	}

	private void addBaseErrors(T model) {
		if(!model.employeeId.isPresent())
			addFieldValidatorError("id", Type.REQUIRED);
		if(model.name.isEmpty())
			addFieldValidatorError("name", Type.EMPTY_STRING);
	}
	
	private void addPaymentMethodErrors(T model) {
		addFieldValidatorErrors(model.paymentMethod.accept(new PaymentMethodFieldsValidatorFactory()));
	}

	protected abstract void addSubTypeErrors(T model);
	
}