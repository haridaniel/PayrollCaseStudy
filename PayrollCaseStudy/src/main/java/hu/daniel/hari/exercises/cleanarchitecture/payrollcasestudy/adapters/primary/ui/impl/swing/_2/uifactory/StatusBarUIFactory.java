package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.uifactory;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarController;

public class StatusBarUIFactory {

	public StatusBarPanel statusBarPanel(EventBus eventBus) {
		StatusBarPanel statusBarPanel = new StatusBarPanel();
		StatusBarController controller = new StatusBarController(statusBarPanel, eventBus);
		return statusBarPanel;
	}
}
