package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field;

import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.viewmodel.FieldValidatorErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.ValidationErrorMessagesModel;

public class FieldValidationErrorPresenter {
	private FieldValidatorErrorFormatter errorFormatter = new FieldValidatorErrorFormatter();
	
	public ValidationErrorMessagesModel present(FieldValidatorException e) {
		return new ValidationErrorMessagesModel(
				e.fieldValidatorErrors.stream()
					.map(it -> errorFormatter.format(it))
					.collect(Collectors.toList())
			);
	}
	
}
