package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.exception.multiple.Error;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.Visitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.VisitorAcceptor;

public interface AddEmployeeError extends Error, 
VisitorAcceptor<AddEmployeeError, AddEmployeeError.AddEmployeeErrorVisitor> 
{
	
//	public abstract <T> T accept(AddEmployeeError.AddEmployeeErrorVisitor<T> visitor);

	public interface AddEmployeeErrorVisitor<R> extends Visitor<R, AddEmployeeError> {
		R visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
		R visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
	}
}