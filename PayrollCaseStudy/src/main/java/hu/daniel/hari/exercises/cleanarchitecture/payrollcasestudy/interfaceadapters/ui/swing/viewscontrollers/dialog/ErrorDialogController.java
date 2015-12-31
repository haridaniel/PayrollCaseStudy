package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.dialog;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.dialog.ErrorDialogView.ErrorDialogListener;

public class ErrorDialogController implements ErrorDialogListener {

	private ErrorDialogView dialog;

	public ErrorDialogController(ErrorDialogView dialog, Throwable throwable) {
		this.dialog = dialog;
		dialog.setListener(this);
		present(throwable);
	}

	private void present(Throwable throwable) {
		dialog.setModel(new ErrorDialogPresenter().present(throwable));
	}

	@Override
	public void onClose() {
		closeDialog();
	}

	private void closeDialog() {
		dialog.dispose();
	}

	private static class ErrorDialogPresenter {
	
		public ErrorDialogViewModel present(Throwable throwable) {
			StringWriter stringWriter = new StringWriter();
			throwable.printStackTrace(new PrintWriter(stringWriter));
			return new ErrorDialogViewModel(stringWriter.toString());
		}
		
	}

}
