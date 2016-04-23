package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field;

import java.util.List;

public class FieldValidatorException extends RuntimeException {
	public final List<FieldValidatorException.FieldValidatorError> fieldValidatorErrors;
	public FieldValidatorException(List<FieldValidatorException.FieldValidatorError> fieldValidatorErrors) {
		this.fieldValidatorErrors = fieldValidatorErrors;
	}
	
	public static class FieldValidatorError {
		public String fieldName;
		public Type type;
		
		public enum Type {
			REQUIRED,
			EMPTY_STRING
		}
		
		public FieldValidatorError(String fieldName, Type errorType) {
			this.fieldName = fieldName;
			type = errorType;
		}
	}
	
}