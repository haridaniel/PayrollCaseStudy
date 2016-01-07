package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing;

import javax.swing.SwingUtilities;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;

public class SwingUI {

	private SwingViewLoader swingViewLoader;

	public SwingUI(UseCaseFactory useCaseFactory) {
		swingViewLoader = new SwingViewLoader(useCaseFactory, createEventBus());
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
