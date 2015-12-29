package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.components.statusbar;

import java.awt.Color;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.events.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.events.DeletedEmployeeEvent;

public class StatusBarController {
	private final Color INFO_COLOR = Color.BLACK;
	private final Color CONFIRM_COLOR = Color.decode("#00AA00");//GREEN
	private final Color ERROR_COLOR = Color.red;

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
		message(message, INFO_COLOR);
	}

	private void confirmMessage(String message) {
		message(message, CONFIRM_COLOR);
	}
	
	private void message(String message, Color iNFO_COLOR2) {
		statusBarView.setMessage(message, iNFO_COLOR2);
	}
	
}
