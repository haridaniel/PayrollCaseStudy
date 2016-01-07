package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.uncaugthexception;

import java.io.PrintWriter;
import java.io.StringWriter;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.uncaugthexception.UncaugthExceptionView.UncaugthExceptionViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.uncaugthexception.UncaugthExceptionView.UncaugthExceptionViewModel;

public class UncaugthExceptionController implements UncaugthExceptionViewListener {

	private UncaugthExceptionView view;
	private ModelConverter modelConverter = new ModelConverter();

	public UncaugthExceptionController(UncaugthExceptionView view, Throwable throwable) {
		this.view = view;
		update(throwable);
	}

	private void update(Throwable throwable) {
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
