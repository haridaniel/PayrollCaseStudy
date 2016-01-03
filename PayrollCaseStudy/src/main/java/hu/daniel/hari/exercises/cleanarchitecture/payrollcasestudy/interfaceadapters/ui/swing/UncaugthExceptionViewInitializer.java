package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;

class UncaugthExceptionViewInitializer {

	private SwingViewLoader swingViewLoader;

	public UncaugthExceptionViewInitializer(SwingViewLoader swingViewLoader) {
		this.swingViewLoader = swingViewLoader;
		init();
	}

	private void init() {
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueue() {
			@Override
			protected void dispatchEvent(AWTEvent event) {
				try {
					super.dispatchEvent(event);
				} catch (Throwable e) {
					onThrowableCatched(e);
					throw e;
				}
			}
		});
	}

	private void onThrowableCatched(Throwable e) {
		SwingUtilities.invokeLater(() -> {
			swingViewLoader.loadUncaugthExceptionView(e);
		});
	}
}