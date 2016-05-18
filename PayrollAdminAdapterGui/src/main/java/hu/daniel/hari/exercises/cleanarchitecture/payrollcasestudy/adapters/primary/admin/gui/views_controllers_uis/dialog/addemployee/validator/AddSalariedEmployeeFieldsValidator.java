package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;

public class AddSalariedEmployeeFieldsValidator extends AddEmployeeFieldsValidator<SalariedEmployeeViewModel> {

	@Override
	protected void addSubTypeErrors(SalariedEmployeeViewModel model) {
		if(model.monthlySalary == null)
			addFieldValidatorError("monthlySalary", Type.REQUIRED);
	}

}
