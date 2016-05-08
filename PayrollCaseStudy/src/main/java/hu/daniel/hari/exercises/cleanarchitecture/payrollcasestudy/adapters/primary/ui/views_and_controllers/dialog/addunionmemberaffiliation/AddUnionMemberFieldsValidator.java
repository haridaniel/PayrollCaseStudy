package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.AbstractFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberView.AddUnionMemberViewOutputModel;

public class AddUnionMemberFieldsValidator extends AbstractFieldsValidator<AddUnionMemberViewOutputModel> {

	@Override
	protected void addErrors(AddUnionMemberViewOutputModel model) {
		if(!model.unionMemberId.isPresent())
			addFieldValidatorError("unionMemberId", Type.REQUIRED);
		if(!model.weeklyDueAmount.isPresent())
			addFieldValidatorError("weeklyDueAmount", Type.REQUIRED);
	}

}
