package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation;

public class NameAlreadyExistsValidationError implements ValidationError {
	public int idOfExistingUser;
	public NameAlreadyExistsValidationError(int idOfExistingUser) {
		this.idOfExistingUser = idOfExistingUser;
	}
	@Override
	public <T> T accept(ValidationErrorVisitor<T> visitor) {
		return visitor.visit(this);
	}
}