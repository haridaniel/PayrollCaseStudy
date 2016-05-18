package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.AbstractFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberView.AddUnionMemberViewOutputModel;

public class AddUnionMemberFieldsValidator extends AbstractFieldsValidator<AddUnionMemberViewOutputModel> {

	@Override
	protected void addErrors(AddUnionMemberViewOutputModel model) {
		if(!model.unionMemberId.isPresent())
			addFieldValidatorError("unionMemberId", Type.REQUIRED);
		if(!model.weeklyDueAmount.isPresent())
			addFieldValidatorError("weeklyDueAmount", Type.REQUIRED);
	}

}
