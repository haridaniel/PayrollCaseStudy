package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog;

import java.util.Optional;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.Controller;

/** Registers, and unregisters from eventbus on close **/
public abstract class AbstractClosableViewController<T extends ClosableView<VL>, VL extends CloseableViewListener> extends 
	AbstractController<T, VL> implements 
	CloseableViewListener 
{

	private Optional<EventBus> eventBus;

	public AbstractClosableViewController(
			EventBus eventBus
			) {
		this.eventBus = Optional.ofNullable(eventBus);
		registerThisToEventbus();
	}

	public AbstractClosableViewController() {
		this(null);
	}

	private void registerThisToEventbus() {
		eventBus.ifPresent(e -> e.register(this));
	}

	@Override
	public void onCloseAction() {
		if(onCloseActionIsAllowed())
			close();
	}
	
	protected abstract boolean onCloseActionIsAllowed();

	protected void close() {
		unregisterThisFromEventBus();
		view.close();
	}

	private void unregisterThisFromEventBus() {
		eventBus.ifPresent(e -> e.unregister(this));
	}


}
