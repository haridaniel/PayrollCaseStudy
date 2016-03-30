package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.mainpanel;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.PayDayPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.payday.PayCheckListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.PayDayController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;

public class PayDayPanelUI {

	public final PayDayPanel view;

	public PayDayPanelUI(EventBus eventBus, UseCaseFactory useCaseFactory) {
		PayCheckListPanel payCheckListPanel = new PayCheckListPanel();
		view = new PayDayPanel(payCheckListPanel);
		PayDayController controller = new PayDayController(view, useCaseFactory, useCaseFactory);
		view.setListener(controller);
		
	}
	

}
