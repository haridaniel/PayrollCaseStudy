package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.statusbar;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;

public abstract class StatusBarUI<V extends StatusBarView> extends 
	UI<V, StatusBarController> 
{

	public StatusBarUI(
			StatusBarController controller,
			V view
			) {
		super(controller, view);
	}

}
