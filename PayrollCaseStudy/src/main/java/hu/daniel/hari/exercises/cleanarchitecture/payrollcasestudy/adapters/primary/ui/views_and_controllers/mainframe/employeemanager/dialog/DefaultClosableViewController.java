package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog;

import com.google.common.eventbus.EventBus;

public abstract class DefaultClosableViewController implements CloseableViewListener {

	private ClosableView<? extends CloseableViewListener> view;
	private EventBus eventBus;

	public DefaultClosableViewController(
			EventBus eventBus
			) {
		this.eventBus = eventBus;
		eventBus.register(this);
	}

	public void setView(ClosableView<? extends CloseableViewListener> view) {
		this.view = view;
	}
	
	@Override
	public void onCloseRequested() {
		if(isAllowedToCloseNow())
			close();
	}
	
	protected abstract boolean isAllowedToCloseNow();

	protected void close() {
		eventBus.unregister(this);
		view.close();
	}


}
