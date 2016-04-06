package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog;

import java.util.Optional;

import com.google.common.eventbus.EventBus;

public abstract class DefaultClosableViewController<T extends ClosableView<? extends CloseableViewListener>> implements CloseableViewListener {

	private T view;
	private Optional<EventBus> eventBus;

	public DefaultClosableViewController(
			EventBus eventBus
			) {
		this.eventBus = Optional.ofNullable(eventBus);
		registerThisToEventbus();
	}

	public DefaultClosableViewController() {
		this(null);
	}

	private void registerThisToEventbus() {
		eventBus.ifPresent(e -> e.register(this));
	}

	public void setView(T view) {
		this.view = view;
	}
	
	protected T getView() {
		return view;
	}
	
	@Override
	public void onCloseRequested() {
		if(isAllowedToCloseNow())
			close();
	}
	
	protected abstract boolean isAllowedToCloseNow();

	protected void close() {
		unregisterThisFromEventBus();
		view.close();
	}

	private void unregisterThisFromEventBus() {
		eventBus.ifPresent(e -> e.unregister(this));
	}


}
