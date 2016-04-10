package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.AddEmployeeValidationError.AddEmployeeValidationErrorVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.NameAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.RequiredFieldValidationError;

public class AddEmployeeValidationErrorFormatter implements AddEmployeeValidationErrorVisitor<String> {

	@Override
	public String visit(RequiredFieldValidationError requiredFieldValidationError) {
		return String.format("%s is missing!", requiredFieldValidationError.fieldName);
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
