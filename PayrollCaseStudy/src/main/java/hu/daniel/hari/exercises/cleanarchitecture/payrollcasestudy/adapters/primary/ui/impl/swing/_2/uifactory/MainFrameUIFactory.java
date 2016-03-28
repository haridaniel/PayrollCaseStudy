package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.uifactory;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameController;

public class MainFrameUIFactory {

	public MainFrameUIFactory(
			
			
			
			
			) {
		// TODO Auto-generated constructor stub
	}
	
	private EventBus createEventBus() {
		return new EventQueueAsyncEventBus();
	}
	
	public MainFrameWindow mainFrameWindow(StatusBarUIFactory statusBarUIFactory) {
		EventBus eventBus = createEventBus();
		MainPanel mainPanel =  null;
		StatusBarPanel statusBarPanel = statusBarUIFactory.statusBarPanel(eventBus);
		MainFrameWindow mainFrameWindow = new MainFrameWindow(mainPanel, statusBarPanel);
		MainFrameController controller = new MainFrameController(mainFrameWindow);
		return mainFrameWindow;
	}

}
