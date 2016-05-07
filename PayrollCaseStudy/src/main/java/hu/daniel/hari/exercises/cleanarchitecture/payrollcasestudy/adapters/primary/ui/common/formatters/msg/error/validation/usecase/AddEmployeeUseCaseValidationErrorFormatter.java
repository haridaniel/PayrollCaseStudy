package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add.AddEmployeeError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add.AddEmployeeError.AddEmployeeErrorVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add.NameAlreadyExistsValidationError;

public class AddEmployeeUseCaseValidationErrorFormatter extends 
	MultipleFormatter<AddEmployeeError> implements 
	AddEmployeeErrorVisitor 
{
	@Override
	protected String format(AddEmployeeError error) {
		return error.accept(this);
	}

	@Override
	public String visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError) {
		return String.format("%s already owns this id!", idAlreadyExistsValidationError.nameOfExistingUser);
	}

	@Override
	public String visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError) {
		return String.format("Name already exists with id: %s", nameAlreadyExistsValidationError.idOfExistingUser);
	}

}
