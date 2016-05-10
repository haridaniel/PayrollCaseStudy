package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.controller.msg;

import java.util.HashMap;
import java.util.Map;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidatorError.Type;

public class FieldValidatorErrorFormatter {

	public final static Map<Type, String> messagesByFieldValidatorErrorType = new HashMap<Type, String>() {{
		put(Type.REQUIRED, "%s is missing");
		put(Type.EMPTY_STRING, "%s should not be empty");
	}};
	
	public String format(FieldValidatorError e) {
		return String.format(messagesByFieldValidatorErrorType.get(e.type), e.fieldName);
	}

}
