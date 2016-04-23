package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation;

public class IdAlreadyExistsValidationError implements ValidationError {
	public String nameOfExistingUser;
	public IdAlreadyExistsValidationError(String nameOfExistingUser) {
		this.nameOfExistingUser = nameOfExistingUser;
	}
	@Override
	public <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor) {
		return visitor.visit(this);
	}
}