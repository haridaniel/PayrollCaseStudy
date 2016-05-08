package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field;

public class FieldValidatorError {
	public String fieldName;
	public FieldValidatorError.Type type;
	
	public enum Type {
		REQUIRED,
		EMPTY_STRING
	}
	
	public FieldValidatorError(String fieldName, FieldValidatorError.Type errorType) {
		this.fieldName = fieldName;
		type = errorType;
	}
}