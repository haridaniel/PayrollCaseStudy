package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.exception.multiple.Error;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.Visitable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.Visitor;

public interface AddEmployeeError extends Error, 
	Visitable<AddEmployeeError.AddEmployeeErrorVisitor, AddEmployeeError> 
{
	
	public interface AddEmployeeErrorVisitor extends Visitor<AddEmployeeErrorVisitor, AddEmployeeError> {
		<R> R visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
		<R> R visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
	}

}