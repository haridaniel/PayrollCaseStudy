package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.EmployeeManagerPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.PayDayPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelController;

public class MainPanelUI {

	public final MainPanel view;

	@Inject
	public MainPanelUI(
			MainPanelController controller,
			EmployeeManagerPanelUI employeeManagerPanelUI,
			PayDayPanelUI payDayPanelUI
			) {
		view = new MainPanel(employeeManagerPanelUI.view, payDayPanelUI.view);
		view.setListener(controller);
		controller.setView(view);
		employeeManagerPanelUI.setObservableCurrentDate(controller.getObservableCurrentDate());
		controller.setDefaultModelToView();
	}

}
