package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.CommissionedEmployeeViewModel;

public class AddCommissionedEmployeeFieldsValidator extends AddEmployeeFieldsValidator<CommissionedEmployeeViewModel> {

	public AddCommissionedEmployeeFieldsValidator(CommissionedEmployeeViewModel model) {
		super(model);
	}

	@Override
	protected void collectSubTypeErrors(CommissionedEmployeeViewModel model) {
		if(model.biWeeklyBaseSalary == null)
			addFieldValidatorError("biWeeklyBaseSalary", Type.REQUIRED);
		if(model.commissionRatePercentage == null)
			addFieldValidatorError("commissionRatePercentage", Type.REQUIRED);
	}

}
