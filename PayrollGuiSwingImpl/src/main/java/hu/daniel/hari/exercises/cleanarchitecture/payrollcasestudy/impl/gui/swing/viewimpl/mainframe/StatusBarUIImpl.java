package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.statusbar.StatusBarController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.statusbar.StatusBarUI;

public class StatusBarUIImpl extends StatusBarUI<StatusBarPanel> {

	@Inject
	public StatusBarUIImpl(
			StatusBarController controller,
			StatusBarPanel statusBarPanel
			) {
		super(controller, statusBarPanel);
	}

}
