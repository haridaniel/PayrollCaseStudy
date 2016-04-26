package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.pay.PayUI;

public abstract class MainPanelUI<V extends MainPanelView> extends
	UI<V, MainPanelController>
{

	@Inject
	public MainPanelUI(
			MainPanelController controller,
			EmployeeManagerUI<?> employeeManagerUI,
			PayUI<?> payUI
			) {
		super(controller);
		employeeManagerUI.setObservableCurrentDate(controller.getObservableCurrentDate());
		payUI.setObservableCurrentDate(controller.getObservableCurrentDate());
	}

	@Inject
	private void initDefaults() {
		controller.setDefaultModelToView();
	}

}
