package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add.NameAlreadyExistsValidationError;

@Deprecated
public interface ValidationError extends VisitorAcceptor {
//	public abstract <T> T accept(ValidationError.ValidationErrorVisitor<T> visitor);
	
	public interface ValidationErrorVisitor<T> extends Visitor<T> {
		T visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
		T visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
	}
}