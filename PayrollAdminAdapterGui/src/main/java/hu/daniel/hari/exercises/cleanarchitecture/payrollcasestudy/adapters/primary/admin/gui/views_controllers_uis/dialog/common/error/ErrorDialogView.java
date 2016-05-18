package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.common.error;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.DialogView;

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