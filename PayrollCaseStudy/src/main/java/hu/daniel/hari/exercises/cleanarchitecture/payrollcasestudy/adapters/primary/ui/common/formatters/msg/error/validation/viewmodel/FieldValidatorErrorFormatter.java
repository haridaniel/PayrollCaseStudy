package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.viewmodel;

import java.util.HashMap;
import java.util.Map;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError.Type;

public class FieldValidatorErrorFormatter {

	public final static Map<Type, String> messagesByFieldValidatorErrorType = new HashMap<Type, String>() {{
		put(Type.REQUIRED, "%s is missing");
		put(Type.EMPTY_STRING, "%s should not be empty");
	}};
	
	public String format(FieldValidatorError e) {
		return String.format(messagesByFieldValidatorErrorType.get(e.type), e.fieldName);
	}

}
