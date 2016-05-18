package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard;

import java.time.LocalDate;
import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.ValidationErrorMessagesConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.DialogView;

public interface AddTimeCardView extends 
	DialogView<AddTimeCardView.AddTimeCardViewListener>,
	ModelConsumer<AddTimeCardView.AddTimeCardViewInputModel>,
	ModelProducer<AddTimeCardView.AddTimeCardViewOutputModel>, 
	ValidationErrorMessagesConsumer
{
	public interface AddTimeCardViewListener extends CloseableViewListener {
		void onAdd();
		void onCancel();
	}
	
	public static class AddTimeCardViewInputModel {
		public String employeeName;
		public LocalDate defaultDate;
	}
	
	public static class AddTimeCardViewOutputModel {
		public LocalDate date;
		public Optional<Integer> workingHourQty;
	}
}