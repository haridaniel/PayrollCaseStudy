package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;

class AddEmployeeFieldsValidator {
	private EmployeeViewModel model;
	private List<FieldValidatorError> fieldValidatorErrors = new ArrayList<>();

	public AddEmployeeFieldsValidator(EmployeeViewModel model) {
		this.model = model;
		collectErrors();
		throwIfThereAreErrors();
	}

	private void collectErrors() {
		if(!model.employeeId.isPresent())
			fieldValidatorErrors.add(new FieldValidatorError("id", Type.REQUIRED));
		if(model.name.isEmpty())
			fieldValidatorErrors.add(new FieldValidatorError("name", Type.EMPTY_STRING));
	}
	
	private void throwIfThereAreErrors() {
		if(!fieldValidatorErrors.isEmpty())
			throw new FieldValidatorException(fieldValidatorErrors);
	}
	
}