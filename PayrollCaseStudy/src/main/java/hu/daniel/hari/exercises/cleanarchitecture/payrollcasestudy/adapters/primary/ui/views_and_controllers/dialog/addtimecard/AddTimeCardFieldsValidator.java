package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.AbstractFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorError.Type;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewOutputModel;

public class AddTimeCardFieldsValidator extends AbstractFieldsValidator<AddTimeCardViewOutputModel> {

	@Override
	protected void addErrors(AddTimeCardViewOutputModel model) {
		if(model.date == null)
			addFieldValidatorError("date", Type.REQUIRED);
		if(!model.workingHourQty.isPresent())
			addFieldValidatorError("workingHourQty", Type.REQUIRED);
	}

}
