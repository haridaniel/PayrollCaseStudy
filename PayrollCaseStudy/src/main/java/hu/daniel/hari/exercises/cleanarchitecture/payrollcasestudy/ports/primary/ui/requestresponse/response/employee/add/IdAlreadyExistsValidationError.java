package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.ReturnValueVisitor;

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