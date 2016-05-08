package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;

public class AddHourlyEmployeeFieldsValidator extends AddEmployeeFieldsValidator<HourlyEmployeeViewModel> {

	@Override
	protected void addSubTypeErrors(HourlyEmployeeViewModel model) {
		if(model.hourlyWage == null)
			addFieldValidatorError("hourlyWage", Type.REQUIRED);
	}

}
