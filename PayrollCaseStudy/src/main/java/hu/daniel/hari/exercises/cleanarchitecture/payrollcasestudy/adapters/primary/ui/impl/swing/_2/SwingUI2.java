package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2;

import javax.swing.SwingUtilities;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.uifactory.MainFrameUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.uifactory.StatusBarUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;

public class SwingUI2 {

	public SwingUI2(UseCaseFactory useCaseFactory) {
		MainFrameUIFactory mainFrameUIFactory = new MainFrameUIFactory();
		
		MainFrameWindow mainFrameWindow = mainFrameUIFactory.mainFrameWindow(new StatusBarUIFactory());
		
		SwingUtilities.invokeLater(() -> {
			mainFrameWindow.setVisible(true);
		});
		
	}
	
}
