package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation;

import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.ValidationErrorMessagesConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.DialogView;

public interface AddUnionMemberView extends
	DialogView<AddUnionMemberView.AddUnionMemberViewListener>,
	ModelConsumer<AddUnionMemberView.AddUnionMemberViewInputModel>,
	ModelProducer<AddUnionMemberView.AddUnionMemberViewOutputModel>,
	ValidationErrorMessagesConsumer
{

	public interface AddUnionMemberViewListener extends CloseableViewListener {
		void onAdd();
		void onCancel();
	}
	
	public static class AddUnionMemberViewInputModel {
		public String employeeName;
	}
	
	public static class AddUnionMemberViewOutputModel {
		public Optional<Integer> unionMemberId;
		public Optional<Integer> weeklyDueAmount;
	}

}
