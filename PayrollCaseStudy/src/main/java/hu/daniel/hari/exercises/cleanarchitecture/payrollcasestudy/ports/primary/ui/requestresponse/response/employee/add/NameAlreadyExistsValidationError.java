package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.Visitor;

public class NameAlreadyExistsValidationError implements AddEmployeeError {
	public int idOfExistingUser;
	public NameAlreadyExistsValidationError(int idOfExistingUser) {
		this.idOfExistingUser = idOfExistingUser;
	}
	@Override
	public <T> T accept(AddEmployeeErrorVisitor<T> visitor) {
		return visitor.visit(this);
	}
	@Override
	public <T> T accept(Visitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}