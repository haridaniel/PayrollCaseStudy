package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError.Type;

public abstract class AbstractFieldsValidator {
	
	private List<FieldValidatorError> fieldValidatorErrors = new ArrayList<>();

	public List<FieldValidatorError> getErrors() {
		return fieldValidatorErrors;
	}

	protected void addFieldValidatorError(String fieldName, Type type) {
		fieldValidatorErrors.add(new FieldValidatorError(fieldName, type));
	}
	protected void addFieldValidatorErrors(Collection<FieldValidatorError> fieldValidatorErrors) {
		this.fieldValidatorErrors.addAll(fieldValidatorErrors);
	}

}