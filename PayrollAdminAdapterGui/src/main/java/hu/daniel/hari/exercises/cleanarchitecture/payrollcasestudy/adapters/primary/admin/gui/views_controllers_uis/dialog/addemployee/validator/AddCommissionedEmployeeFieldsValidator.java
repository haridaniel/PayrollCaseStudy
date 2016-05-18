package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.CommissionedEmployeeViewModel;

public class AddCommissionedEmployeeFieldsValidator extends AddEmployeeFieldsValidator<CommissionedEmployeeViewModel> {

	@Override
	protected void addSubTypeErrors(CommissionedEmployeeViewModel model) {
		if(model.biWeeklyBaseSalary == null)
			addFieldValidatorError("biWeeklyBaseSalary", Type.REQUIRED);
		if(model.commissionRatePercentage == null)
			addFieldValidatorError("commissionRatePercentage", Type.REQUIRED);
	}

}
