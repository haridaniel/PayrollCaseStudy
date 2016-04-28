package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.statusbar;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.event.EventMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AffiliationChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.PaymentsFulfilledEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.Controller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarView.StatusBarViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarView.StatusBarViewModel.MessageType;

public class StatusBarController implements Controller<StatusBarView> {

	private StatusBarView view;
	private EventMessageFormatter messageFormatter = new EventMessageFormatter();

	@Inject
	public StatusBarController(EventBus eventBus) {
		eventBus.register(this);
	}
	
	@Override
	public void setView(StatusBarView view) {
		this.view = view;
	}
	
	@Subscribe
	public void on(AddedEmployeeEvent event) {
		confirmMessage(messageFormatter.format(event));
	}
	
	@Subscribe
	public void on(AddedTimeCardEvent event) {
		confirmMessage(messageFormatter.format(event));
	}
	
	@Subscribe
	public void on(DeletedEmployeeEvent event) {
		infoMessage(messageFormatter.format(event));
	}

	@Subscribe
	public void on(PaymentsFulfilledEvent event) {
		infoMessage(messageFormatter.format(event));
	}

	@Subscribe
	public void on(AffiliationChangedEvent event) {
		infoMessage(messageFormatter.format(event));
	}

	
	private void infoMessage(String message) {
		view.setModel(new StatusBarViewModel(message, MessageType.INFO));
	}

	private void confirmMessage(String message) {
		view.setModel(new StatusBarViewModel(message, MessageType.CONFIRM));
	}
	
}
