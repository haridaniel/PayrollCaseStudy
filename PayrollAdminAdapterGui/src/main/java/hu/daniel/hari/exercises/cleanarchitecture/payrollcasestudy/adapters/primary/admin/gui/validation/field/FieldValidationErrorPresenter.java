package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field;

import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.controller.msg.FieldValidatorErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.ValidationErrorMessagesModel;

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
