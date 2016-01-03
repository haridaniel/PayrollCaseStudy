package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.StatusBarView.StatusBarViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.StatusBarView.StatusBarViewModel.MessageType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.messageformatter.MessageFormatter;

public class StatusBarController {

	private StatusBarView statusBarView;
	private MessageFormatter messageFormatter;

	public StatusBarController(StatusBarView statusBarView, EventBus eventBus) {
		this.statusBarView = statusBarView;
		this.messageFormatter = new MessageFormatter();
		eventBus.register(this);
	}
	
	@Subscribe
	public void onAddedEmployee(AddedEmployeeEvent event) {
		confirmMessage(messageFormatter.format(event));
	}
	
	@Subscribe
	public void onDeletedEmployee(DeletedEmployeeEvent event) {
		infoMessage(messageFormatter.format(event));
	}
	
	private void infoMessage(String message) {
		statusBarView.setModel(new StatusBarViewModel(message, MessageType.INFO));
	}

	private void confirmMessage(String message) {
		statusBarView.setModel(new StatusBarViewModel(message, MessageType.CONFIRM));
	}
	
}
