package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.statusbar;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.View;

public interface StatusBarView extends 
	View, 
	ModelConsumer<StatusBarView.StatusBarViewModel>
{

	public static class StatusBarViewModel {
		public String message;
		public MessageType messageType;
		
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