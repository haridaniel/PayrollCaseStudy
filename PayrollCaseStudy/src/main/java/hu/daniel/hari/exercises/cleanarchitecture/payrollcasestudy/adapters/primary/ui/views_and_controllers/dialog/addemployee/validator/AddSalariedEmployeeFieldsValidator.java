package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;

public class AddSalariedEmployeeFieldsValidator extends AddEmployeeFieldsValidator<SalariedEmployeeViewModel> {

	@Override
	protected void addSubTypeErrors(SalariedEmployeeViewModel model) {
		if(model.monthlySalary == null)
			addFieldValidatorError("monthlySalary", Type.REQUIRED);
	}

}
