package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.error;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public abstract class ErrorDialogUI<V extends ErrorDialogView> extends
	UI<V, ErrorDialogController>
{

	@Inject
	public ErrorDialogUI(
			ErrorDialogController controller,
			V view
			) {
		super(controller, view);
	}
	
	public void show(Throwable e) {
		controller.setThrowable(e);
		controller.show();
	}
	
}
