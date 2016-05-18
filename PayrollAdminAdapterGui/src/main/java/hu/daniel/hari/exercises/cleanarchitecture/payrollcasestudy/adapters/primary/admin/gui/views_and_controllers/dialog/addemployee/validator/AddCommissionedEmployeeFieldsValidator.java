package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addemployee.AddEmployeeView.CommissionedEmployeeViewModel;

public class AddCommissionedEmployeeFieldsValidator extends AddEmployeeFieldsValidator<CommissionedEmployeeViewModel> {

	@Override
	protected void addSubTypeErrors(CommissionedEmployeeViewModel model) {
		if(model.biWeeklyBaseSalary == null)
			addFieldValidatorError("biWeeklyBaseSalary", Type.REQUIRED);
		if(model.commissionRatePercentage == null)
			addFieldValidatorError("commissionRatePercentage", Type.REQUIRED);
	}

}
