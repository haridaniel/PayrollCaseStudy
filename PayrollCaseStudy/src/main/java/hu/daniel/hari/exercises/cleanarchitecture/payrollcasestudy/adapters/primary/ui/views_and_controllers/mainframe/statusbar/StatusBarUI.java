package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public class StatusBarUI<V extends StatusBarView> extends UI<V, StatusBarController> {

	public StatusBarUI(StatusBarController controller) {
		super(controller);
	}

}
