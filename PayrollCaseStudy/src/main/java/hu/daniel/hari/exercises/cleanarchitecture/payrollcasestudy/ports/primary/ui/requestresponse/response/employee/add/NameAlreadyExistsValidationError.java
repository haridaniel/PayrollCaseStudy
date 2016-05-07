package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add;

public class NameAlreadyExistsValidationError implements AddEmployeeError {
	public int idOfExistingUser;
	public NameAlreadyExistsValidationError(int idOfExistingUser) {
		this.idOfExistingUser = idOfExistingUser;
	}
	@Override
	public <R> R accept(AddEmployeeErrorVisitor visitor) {
		return visitor.visit(this);
	}
}