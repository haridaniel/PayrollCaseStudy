package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui;

import javax.inject.Inject;
import javax.swing.SwingUtilities;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.MainPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.StatusBarUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;

public class MainFrameUI {

	public final MainFrameWindow view;
	
	@Inject
	public MainFrameUI(
			MainFrameController controller,
			MainPanelUI mainPanelUI,
			StatusBarUI statusBarUI 
			) {
		view = new MainFrameWindow(mainPanelUI.mainPanel, statusBarUI.statusBarPanel);
		controller.setView(view);
	}
	
	public void show() {
		SwingUtilities.invokeLater(() -> {
			view.setVisible(true);
		});
	}
	
}
