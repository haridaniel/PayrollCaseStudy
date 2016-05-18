package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation;

import java.util.List;

public class ValidationErrorMessagesModel {
	public List<String> validationErrorMessages;
	public ValidationErrorMessagesModel(List<String> validationErrorMessages) {
		this.validationErrorMessages = validationErrorMessages;
	}
}