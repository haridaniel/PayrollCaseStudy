package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarUI;

public class StatusBarUIImpl extends StatusBarUI<StatusBarPanel> {

	private StatusBarPanel statusBarPanel;

	@Inject
	public StatusBarUIImpl(
			StatusBarController controller,
			StatusBarPanel statusBarPanel
			) {
		super(controller);
		this.statusBarPanel = statusBarPanel;
	}

	@Override
	protected StatusBarPanel createView() {
		return statusBarPanel;
	}
	
}
