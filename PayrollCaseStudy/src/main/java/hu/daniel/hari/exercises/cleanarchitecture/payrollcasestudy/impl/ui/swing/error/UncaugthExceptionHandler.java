package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.error;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.Provider;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.error.ErrorDialogUI;

@Singleton
public class UncaugthExceptionHandler {
	
	private Provider<ErrorDialogUI<?>> errorDialogUIProvider;

	@Inject
	public UncaugthExceptionHandler(
			Provider<ErrorDialogUI<?>> errorDialogUIProvider
			) {
		this.errorDialogUIProvider = errorDialogUIProvider;
		initSystemEventQueueDispatchHook();
	}
	
	private void initSystemEventQueueDispatchHook() {
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
		errorDialogUIProvider.get().show(e);
	}

}
