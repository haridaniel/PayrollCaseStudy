package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;

public class ConfirmDialogUIImpl implements ConfirmDialogUI {
	private MainFrameUIImpl mainFrameUIImpl;

	@Inject
	public ConfirmDialogUIImpl(
			MainFrameUIImpl mainFrameUIImpl
			) {
		this.mainFrameUIImpl = mainFrameUIImpl;
	}
	
	@Override
	public void show(String message, ConfirmDialogListener confirmDialogListener) {
		new ConfirmDialog(mainFrameUIImpl.view, message, confirmDialogListener)
			.showIt();
	}

}
