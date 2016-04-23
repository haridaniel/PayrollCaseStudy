package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common.ConfirmDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common.ConfirmDialog.ConfirmDialogListener;

public class ConfirmDialogUI {
	private Provider<JFrame> rootFrameProvider;

	@Inject
	public ConfirmDialogUI(
			Provider<JFrame> rootFrameProvider
			) {
		this.rootFrameProvider = rootFrameProvider;
	}
	
	public void show(String message, ConfirmDialogListener confirmDialogListener) {
		new ConfirmDialog(rootFrameProvider.get(), message, confirmDialogListener)
			.showDialog();
	}

}
