package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing;

import javax.swing.SwingUtilities;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class SwingUI {

	private ViewFactory viewFactory;

	public SwingUI(UseCaseFactory useCaseFactory) {
		viewFactory = new ViewFactory(useCaseFactory, createEventBus());
		new ErrorDialogUncaugthExceptionHandler(viewFactory).init();
	}

	private EventBus createEventBus() {
		return new EventQueueAsyncEventBus();
	}

	public void show() {
		SwingUtilities.invokeLater(() -> {
			viewFactory.mainFrameView().doShow();
		});
	}

}
