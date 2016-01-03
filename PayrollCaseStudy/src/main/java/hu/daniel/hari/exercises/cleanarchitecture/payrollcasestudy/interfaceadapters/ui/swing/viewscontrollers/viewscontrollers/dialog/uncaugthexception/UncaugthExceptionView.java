package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.dialog.uncaugthexception;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.ModelConsumer;

public interface UncaugthExceptionView extends 
	HasListener<UncaugthExceptionView.UncaugthExceptionViewListener>,
	ModelConsumer<UncaugthExceptionView.UncaugthExceptionViewModel>
{

	void close();
	
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