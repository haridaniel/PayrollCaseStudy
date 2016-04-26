package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.error;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DialogView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;

public interface ErrorDialogView extends 
	DialogView<CloseableViewListener>,
	ModelConsumer<ErrorDialogView.ErrorViewModel>
{
	
	public class ErrorViewModel {
		public String errorMessage;

		public ErrorViewModel(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
	}

}