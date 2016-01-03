package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.dialog.uncaugthexception;

import java.io.PrintWriter;
import java.io.StringWriter;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.dialog.uncaugthexception.UncaugthExceptionView.UncaugthExceptionViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.dialog.uncaugthexception.UncaugthExceptionView.UncaugthExceptionViewModel;

public class UncaugthExceptionController implements UncaugthExceptionViewListener {

	private UncaugthExceptionView view;
	private ModelConverter modelConverter = new ModelConverter();

	public UncaugthExceptionController(UncaugthExceptionView view, Throwable throwable) {
		this.view = view;
		updateViewModel(throwable);
	}

	private void updateViewModel(Throwable throwable) {
		view.setModel(modelConverter.toViewModel(throwable));
	}

	@Override
	public void onClose() {
		close();
	}

	private void close() {
		view.close();
	}

	private static class ModelConverter {
	
		public UncaugthExceptionViewModel toViewModel(Throwable throwable) {
			StringWriter stringWriter = new StringWriter();
			throwable.printStackTrace(new PrintWriter(stringWriter));
			return new UncaugthExceptionViewModel(stringWriter.toString());
		}
		
	}

}
