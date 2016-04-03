package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1;

import javax.swing.SwingUtilities;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;

public class SwingUI {

	private SwingViewLoader swingViewLoader;

	public SwingUI(UseCaseFactories useCaseFactories) {
		swingViewLoader = new SwingViewLoader(useCaseFactories, createEventBus());
		new UncaugthExceptionViewInitializer(swingViewLoader);
		show();
	}

	private EventBus createEventBus() {
		return new EventQueueAsyncEventBus();
	}

	private void show() {
		SwingUtilities.invokeLater(() -> {
			swingViewLoader.loadMainFrameView();
		});
	}

}
