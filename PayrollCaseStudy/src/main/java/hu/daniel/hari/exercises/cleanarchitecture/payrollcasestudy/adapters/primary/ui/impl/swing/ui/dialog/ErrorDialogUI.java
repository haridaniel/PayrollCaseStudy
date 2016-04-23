package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common.ErrorDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.error.ErrorDialogController;

public class ErrorDialogUI {
	
	private ErrorDialog view;
	private ErrorDialogController controller;

	@Inject
	public ErrorDialogUI(
			ErrorDialogController controller, 
			Provider<JFrame> rootFrameProvider
			) {
		this.controller = controller;
		view = new ErrorDialog(rootFrameProvider.get());
		view.setViewListener(controller);
		controller.setView(view);
	}
	
	public void show(Throwable e) {
		controller.setThrowable(e);
		view.showDialog();
	}
	
}
