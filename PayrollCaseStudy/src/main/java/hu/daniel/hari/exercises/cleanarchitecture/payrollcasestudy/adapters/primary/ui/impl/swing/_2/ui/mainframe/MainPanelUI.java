package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.mainpanel.EmployeeManagerPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.mainpanel.PayDayPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.MainPanel;

public class MainPanelUI {

	public final MainPanel mainPanel;

	@Inject
	public MainPanelUI(
			EmployeeManagerPanelUI employeeManagerPanelUI,
			PayDayPanelUI payDayPanelUI
			) {
		mainPanel = new MainPanel(employeeManagerPanelUI.view, payDayPanelUI.view);
	}
	

}
