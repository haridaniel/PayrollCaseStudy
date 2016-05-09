package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.common;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.error.ErrorDialogController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.error.ErrorDialogUI;

public class ErrorDialogUIImpl extends
	ErrorDialogUI<ErrorDialog>
{

	@Inject
	public ErrorDialogUIImpl(
			ErrorDialogController controller, 
			MainFrameUIImpl mainFrameUIImpl
			) {
		super(controller, new ErrorDialog(mainFrameUIImpl.view));
	}
	
}
