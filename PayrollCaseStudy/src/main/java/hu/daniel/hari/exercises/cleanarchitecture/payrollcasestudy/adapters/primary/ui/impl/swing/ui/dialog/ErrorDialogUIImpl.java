package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common.ErrorDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.error.ErrorDialogController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.error.ErrorDialogUI;

public class ErrorDialogUIImpl extends
	ErrorDialogUI<ErrorDialog>
{

	@Inject
	public ErrorDialogUIImpl(
			ErrorDialogController controller, 
			Provider<JFrame> rootFrameProvider
			) {
		super(controller, new ErrorDialog(rootFrameProvider.get()));
	}
	
}
