package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.statusbar;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.controller.msg.EventMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.AffiliationChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.PaymentsFulfilledEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.UpdatedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.Controller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarView.StatusBarViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarView.StatusBarViewModel.MessageType;

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
	public void on(UpdatedTimeCardEvent event) {
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
		confirmMessage(messageFormatter.format(event));
	}

	
	private void infoMessage(String message) {
		view.setModel(new StatusBarViewModel(message, MessageType.INFO));
	}

	private void confirmMessage(String message) {
		view.setModel(new StatusBarViewModel(message, MessageType.CONFIRM));
	}
	
}
