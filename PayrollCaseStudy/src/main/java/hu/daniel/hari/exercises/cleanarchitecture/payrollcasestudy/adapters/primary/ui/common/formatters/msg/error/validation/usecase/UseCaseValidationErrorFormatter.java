package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.NameAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.ValidationError.AddEmployeeValidationErrorVisitor;

public class UseCaseValidationErrorFormatter implements AddEmployeeValidationErrorVisitor<String> {

	@Override
	public String visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError) {
		return String.format("%s already owns this id!", idAlreadyExistsValidationError.nameOfExistingUser);
	}

	@Override
	public String visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError) {
		return String.format("Name already exists with id: %s", nameAlreadyExistsValidationError.idOfExistingUser);
	}

}
