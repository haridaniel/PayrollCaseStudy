package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.ClosableView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;

public interface AddTimeCardView extends ClosableView<AddTimeCardView.AddTimeCardViewListener>,
	ModelConsumer<AddTimeCardView.AddTimeCardViewInputModel>,
	ModelProducer<AddTimeCardView.AddTimeCardViewOutputModel>
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