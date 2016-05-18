package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.add;

public class IdAlreadyExistsValidationError implements AddEmployeeError {
	public String nameOfExistingUser;
	public IdAlreadyExistsValidationError(String nameOfExistingUser) {
		this.nameOfExistingUser = nameOfExistingUser;
	}
	
	@Override
	public <R> R accept(AddEmployeeErrorVisitor visitor) {
		return visitor.visit(this);
	}
	
}