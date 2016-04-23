package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorException.FieldValidatorError.Type;

public abstract class AbstractFieldsValidator<T> {
	
	private List<FieldValidatorError> fieldValidatorErrors;

	public List<FieldValidatorError> getErrors(T model) {
		fieldValidatorErrors = new ArrayList<>();
		addErrors(model);
		return fieldValidatorErrors;
	}

	protected abstract void addErrors(T model);

	protected void addFieldValidatorError(String fieldName, Type type) {
		fieldValidatorErrors.add(new FieldValidatorError(fieldName, type));
	}
	protected void addFieldValidatorErrors(Collection<FieldValidatorError> fieldValidatorErrors) {
		this.fieldValidatorErrors.addAll(fieldValidatorErrors);
	}

}