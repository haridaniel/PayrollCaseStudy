package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.ViewLoader;

class UncaugthExceptionViewInitializer {

	private ViewLoader viewLoader;

	public UncaugthExceptionViewInitializer(ViewLoader viewLoader) {
		this.viewLoader = viewLoader;
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
			viewLoader.loadUncaugthExceptionView(e);
		});
	}
}