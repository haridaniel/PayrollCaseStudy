package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui;

import javax.swing.SwingUtilities;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.MainPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.StatusBarUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;

public class MainFrameUI {

	private MainFrameWindow window;
	private MainFrameController controller;

	public MainFrameUI(
			UseCaseFactory useCaseFactory
			
			) {
		EventBus eventBus = createEventBus();
		
		StatusBarUI statusBarUI = new StatusBarUI(eventBus);
		MainPanelUI mainPanelUI = new MainPanelUI(eventBus, useCaseFactory);
		
		window = new MainFrameWindow(mainPanelUI.mainPanel, statusBarUI.statusBarPanel);
		controller = new MainFrameController(window);
	}
	
	private EventBus createEventBus() {
		return new EventQueueAsyncEventBus();
	}

	public void show() {
		SwingUtilities.invokeLater(() -> {
			window.setVisible(true);
		});
	}
	
}
