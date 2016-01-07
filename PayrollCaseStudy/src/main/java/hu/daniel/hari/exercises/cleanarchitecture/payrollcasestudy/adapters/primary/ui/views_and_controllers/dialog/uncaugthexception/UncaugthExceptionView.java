package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.uncaugthexception;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.Closeable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;

public interface UncaugthExceptionView extends 
	HasListener<UncaugthExceptionView.UncaugthExceptionViewListener>,
	ModelConsumer<UncaugthExceptionView.UncaugthExceptionViewModel>,
	Closeable
{

	public interface UncaugthExceptionViewListener {
		void onClose();
	}

	public class UncaugthExceptionViewModel {
		public String stackTraceString;

		public UncaugthExceptionViewModel(String stackTraceString) {
			this.stackTraceString = stackTraceString;
		}
	}

}