package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation;

import java.util.List;

public class ValidationErrorMessagesModel {
	public List<String> validationErrorMessages;
	public ValidationErrorMessagesModel(List<String> validationErrorMessages) {
		this.validationErrorMessages = validationErrorMessages;
	}
}