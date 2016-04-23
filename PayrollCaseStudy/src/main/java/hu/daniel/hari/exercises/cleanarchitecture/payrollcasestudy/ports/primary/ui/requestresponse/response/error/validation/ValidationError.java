package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation;

public interface ValidationError {
	
	public abstract <T> T accept(ValidationError.AddEmployeeValidationErrorVisitor<T> visitor);
	
	public interface AddEmployeeValidationErrorVisitor<T> {
		T visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
		T visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
	}
}