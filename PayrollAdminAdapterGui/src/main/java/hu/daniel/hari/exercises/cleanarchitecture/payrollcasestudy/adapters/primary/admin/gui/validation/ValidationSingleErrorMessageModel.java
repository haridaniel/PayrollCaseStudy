package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation;

import java.util.Arrays;

public class ValidationSingleErrorMessageModel extends ValidationErrorMessagesModel {

	public ValidationSingleErrorMessageModel(String validationErrorMessage) {
		super(Arrays.asList(validationErrorMessage));
	}

}
