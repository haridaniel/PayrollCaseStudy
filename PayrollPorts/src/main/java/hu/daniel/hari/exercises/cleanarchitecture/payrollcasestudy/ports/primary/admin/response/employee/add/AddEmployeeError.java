package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.exception.multiple.Error;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.exception.multiple.Visitable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.exception.multiple.Visitor;

public interface AddEmployeeError extends Error, 
	Visitable<AddEmployeeError.AddEmployeeErrorVisitor, AddEmployeeError> 
{
	
	public interface AddEmployeeErrorVisitor extends Visitor<AddEmployeeErrorVisitor, AddEmployeeError> {
		<R> R visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
		<R> R visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
	}

}