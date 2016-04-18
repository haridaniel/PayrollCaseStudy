package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;

public abstract class AddEmployeeFieldsValidator<T extends EmployeeViewModel> {
	private T model;
	private List<FieldValidatorError> fieldValidatorErrors = new ArrayList<>();

	public AddEmployeeFieldsValidator(T model) {
		this.model = model;
		collectErrors();
		collectSubTypeErrors(model);
		throwIfThereAreErrors();
	}

	private void collectErrors() {
		if(!model.employeeId.isPresent()) {
			addFieldValidatorError("id", Type.REQUIRED);
		}
		if(model.name.isEmpty())
			addFieldValidatorError("name", Type.EMPTY_STRING);
	}

	protected void addFieldValidatorError(String fieldName, Type required) {
		fieldValidatorErrors.add(new FieldValidatorError(fieldName, required));
	}
	
	protected abstract void collectSubTypeErrors(T model);
	
	private void throwIfThereAreErrors() {
		if(!fieldValidatorErrors.isEmpty())
			throw new FieldValidatorException(fieldValidatorErrors);
	}
	
}