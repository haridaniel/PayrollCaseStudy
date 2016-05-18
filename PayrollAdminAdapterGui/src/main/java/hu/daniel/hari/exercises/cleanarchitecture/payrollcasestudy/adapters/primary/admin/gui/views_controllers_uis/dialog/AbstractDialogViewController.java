package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog;

import java.util.Optional;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Controller;

/** Registers, and unregisters from eventbus on close **/
public abstract class AbstractDialogViewController<T extends DialogView<VL>, VL extends CloseableViewListener> extends 
	AbstractController<T, VL> implements 
	CloseableViewListener 
{

	private Optional<EventBus> eventBus;

	public AbstractDialogViewController(
			EventBus eventBus
			) {
		this.eventBus = Optional.ofNullable(eventBus);
		registerThisToEventbus();
	}

	public AbstractDialogViewController() {
		this(null);
	}

	private void registerThisToEventbus() {
		eventBus.ifPresent(e -> e.register(this));
	}

	public void show() {
		getView().showIt();
	}
	
	@Override
	public void onCloseAction() {
		if(onCloseAction_shouldCloseAutomatically())
			close();
	}
	
	protected abstract boolean onCloseAction_shouldCloseAutomatically();

	protected void close() {
		unregisterThisFromEventBus();
		getView().close();
	}

	private void unregisterThisFromEventBus() {
		eventBus.ifPresent(e -> e.unregister(this));
	}

}
