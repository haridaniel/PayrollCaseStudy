package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.error;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DefaultClosableViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.error.ErrorDialogView.ErrorViewModel;

public class ErrorDialogController extends DefaultClosableViewController<ErrorDialogView> {

	@Inject
	public ErrorDialogController() {
	}

	public void setThrowable(Throwable throwable) {
		getView().setModel(new Presenter().toViewModel(throwable));
	}
	
	@Override
	protected boolean isAllowedToCloseNow() {
		return true;
	}

	private static class Presenter {
		
		public ErrorViewModel toViewModel(Throwable throwable) {
			StringWriter stringWriter = new StringWriter();
			throwable.printStackTrace(new PrintWriter(stringWriter));
			return new ErrorViewModel(stringWriter.toString());
		}
		
	}
}
