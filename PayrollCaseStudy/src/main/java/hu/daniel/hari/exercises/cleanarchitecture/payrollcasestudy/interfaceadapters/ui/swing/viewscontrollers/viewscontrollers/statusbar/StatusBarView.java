package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.ModelConsumer;

public interface StatusBarView extends
	ModelConsumer<StatusBarView.StatusBarViewModel>

{

	public static class StatusBarViewModel {
		String message;
		MessageType messageType;
		
		public StatusBarViewModel(String message, MessageType messageType) {
			this.message = message;
			this.messageType = messageType;
		}

		public enum MessageType {
			INFO,
			CONFIRM,
			ERROR
		}
	}
	
}