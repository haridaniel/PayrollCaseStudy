package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.util;

import java.util.concurrent.Executor;

import javax.inject.Singleton;
import javax.swing.SwingUtilities;

import com.google.common.eventbus.AsyncEventBus;

@Singleton
public class EventQueueAsyncEventBus extends AsyncEventBus {

	public EventQueueAsyncEventBus() {
		super(new EventQueueExecutor());
	}

	@Override
	public void post(Object event) {
		postWithEventQueue(event);
	}

	private void postWithEventQueue(Object event) {
		SwingUtilities.invokeLater(() -> 
			super.post(event)
		);
		
	}
	
	private static class EventQueueExecutor implements Executor {
		@Override
		public void execute(Runnable command) {
			SwingUtilities.invokeLater(command);
		}
	}

}
