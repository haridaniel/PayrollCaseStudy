package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarView.StatusBarViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarView.StatusBarViewModel.MessageType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.messageformatter.StatusBarMessageFormatter;

public class StatusBarController {

	private StatusBarView statusBarView;
	private StatusBarMessageFormatter messageFormatter = new StatusBarMessageFormatter();

	public StatusBarController(StatusBarView statusBarView, EventBus eventBus) {
		this.statusBarView = statusBarView;
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
