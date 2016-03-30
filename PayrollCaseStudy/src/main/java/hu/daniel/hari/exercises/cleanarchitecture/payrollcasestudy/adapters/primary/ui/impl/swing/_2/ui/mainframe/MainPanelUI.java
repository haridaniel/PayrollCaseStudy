package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.mainpanel.EmployeeManagerPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.mainpanel.PayDayPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;

public class MainPanelUI {

	public final MainPanel mainPanel;

	public MainPanelUI(EventBus eventBus, UseCaseFactory useCaseFactory) {
		
		EmployeeManagerPanelUI employeeManagerPanelUI = new EmployeeManagerPanelUI(eventBus, useCaseFactory);
		PayDayPanelUI payDayPanelUI = new PayDayPanelUI(eventBus, useCaseFactory);
		
		mainPanel = new MainPanel(employeeManagerPanelUI.view, payDayPanelUI.view);
		ViewLoader viewLoader = null;
		MainPanelController controller = new MainPanelController(mainPanel, viewLoader);
		mainPanel.setListener(controller);
	}
	

}
