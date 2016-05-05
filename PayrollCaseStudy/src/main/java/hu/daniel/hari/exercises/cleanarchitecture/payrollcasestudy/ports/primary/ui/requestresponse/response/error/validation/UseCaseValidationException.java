package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation;

import java.util.List;

@Deprecated
public class UseCaseValidationException extends RuntimeException {
	public final List<ValidationError> validationErrors;
	public UseCaseValidationException(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}
}