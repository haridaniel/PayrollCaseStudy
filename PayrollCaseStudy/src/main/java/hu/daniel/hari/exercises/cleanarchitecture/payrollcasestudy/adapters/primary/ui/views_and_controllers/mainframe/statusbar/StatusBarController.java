package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.event.EventMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.PaymentsFulfilledEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarView.StatusBarViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarView.StatusBarViewModel.MessageType;

public class StatusBarController {

	private StatusBarView statusBarView;
	private EventMessageFormatter messageFormatter = new EventMessageFormatter();

	@Inject
	public StatusBarController(EventBus eventBus) {
		eventBus.register(this);
	}
	
	public void setView(StatusBarView statusBarView) {
		this.statusBarView = statusBarView;
	}
	
	@Subscribe
	public void onAddedEmployee(AddedEmployeeEvent event) {
		confirmMessage(messageFormatter.format(event));
	}
	
	@Subscribe
	public void onAddedTimeCard(AddedTimeCardEvent event) {
		confirmMessage(messageFormatter.format(event));
	}
	
	@Subscribe
	public void onDeletedEmployee(DeletedEmployeeEvent event) {
		infoMessage(messageFormatter.format(event));
	}

	@Subscribe
	public void onPaymentsFulfilled(PaymentsFulfilledEvent event) {
		infoMessage(messageFormatter.format(event));
	}
	
	private void infoMessage(String message) {
		statusBarView.setModel(new StatusBarViewModel(message, MessageType.INFO));
	}

	private void confirmMessage(String message) {
		statusBarView.setModel(new StatusBarViewModel(message, MessageType.CONFIRM));
	}
	
}
