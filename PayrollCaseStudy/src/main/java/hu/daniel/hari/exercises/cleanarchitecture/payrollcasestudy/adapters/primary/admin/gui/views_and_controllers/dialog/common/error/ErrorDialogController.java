package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.error;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.AbstractDialogViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.error.ErrorDialogView.ErrorViewModel;

public class ErrorDialogController extends 
	AbstractDialogViewController<ErrorDialogView, CloseableViewListener> 
{

	public void setThrowable(Throwable throwable) {
		getView().setModel(new Presenter().toViewModel(throwable));
	}
	
	@Override
	protected boolean onCloseAction_shouldCloseAutomatically() {
		return true;
	}

	@Override
	protected CloseableViewListener getViewListener() {
		return this;
	}

	private static class Presenter {
		
		public ErrorViewModel toViewModel(Throwable throwable) {
			StringWriter stringWriter = new StringWriter();
			throwable.printStackTrace(new PrintWriter(stringWriter));
			return new ErrorViewModel(stringWriter.toString());
		}
		
	}
	
}
