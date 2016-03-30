package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarController;

public class StatusBarUI {

	public final StatusBarPanel statusBarPanel;

	public StatusBarUI(EventBus eventBus) {
		statusBarPanel = new StatusBarPanel();
		new StatusBarController(statusBarPanel, eventBus);
	}
	
}
