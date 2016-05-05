package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.ValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.Visitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.ValidationError.ValidationErrorVisitor;

public class IdAlreadyExistsValidationError implements AddEmployeeError {
	public String nameOfExistingUser;
	public IdAlreadyExistsValidationError(String nameOfExistingUser) {
		this.nameOfExistingUser = nameOfExistingUser;
	}
//	@Override
//	public <T> T accept(AddEmployeeErrorVisitor<T> visitor) {
//		return visitor.visit(this);
//	}
//	@Override
//	public <T> T accept(Visitor<T> visitor) {
//		// TODO Auto-generated method stub
//		return visitor.;
//	}
//	@Override
//	public <T> T accept(Visitor<T> visitor) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public <T, V extends AddEmployeeErrorVisitor<T>> T accept(V visitor) {
//		return null;
//	}
	@Override
	public <R> R accept(Visitor<R, AddEmployeeError> visitor) {
		return visitor.;
	}
	
}