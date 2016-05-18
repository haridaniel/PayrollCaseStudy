package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;

public class AddHourlyEmployeeFieldsValidator extends AddEmployeeFieldsValidator<HourlyEmployeeViewModel> {

	@Override
	protected void addSubTypeErrors(HourlyEmployeeViewModel model) {
		if(model.hourlyWage == null)
			addFieldValidatorError("hourlyWage", Type.REQUIRED);
	}

}
