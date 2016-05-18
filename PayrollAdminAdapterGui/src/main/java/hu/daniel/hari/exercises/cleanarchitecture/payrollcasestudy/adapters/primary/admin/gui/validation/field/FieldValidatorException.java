package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field;

import java.util.List;

public class FieldValidatorException extends RuntimeException {
	public final List<FieldValidatorError> fieldValidatorErrors;
	public FieldValidatorException(List<FieldValidatorError> fieldValidatorErrors) {
		this.fieldValidatorErrors = fieldValidatorErrors;
	}
	
}