package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.dialog.common;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.MainFrameUIImpl;

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
