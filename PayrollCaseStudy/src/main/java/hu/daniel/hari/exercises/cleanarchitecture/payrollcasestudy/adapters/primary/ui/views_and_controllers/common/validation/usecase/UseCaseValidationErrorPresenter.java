package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.usecase;

import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.usecase.UseCaseValidationErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.UseCaseValidationException;

public class UseCaseValidationErrorPresenter {
	private UseCaseValidationErrorFormatter errorFormatter = new UseCaseValidationErrorFormatter();
	
	public ValidationErrorMessagesModel present(UseCaseValidationException e) {
		return new ValidationErrorMessagesModel(
				e.validationErrors.stream()
					.map(it -> it.accept(errorFormatter))
					.collect(Collectors.toList())
			);
	}
	
}
