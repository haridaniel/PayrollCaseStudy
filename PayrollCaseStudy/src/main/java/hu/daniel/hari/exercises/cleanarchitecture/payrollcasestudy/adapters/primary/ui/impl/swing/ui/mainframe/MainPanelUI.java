package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.EmployeeManagerUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.PayUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelController;

public class MainPanelUI {

	public final MainPanel view;

	@Inject
	public MainPanelUI(
			MainPanelController controller,
			EmployeeManagerUIImpl employeeManagerUIImpl,
			PayUIImpl payPanelUI
			) {
		view = new MainPanel(employeeManagerUIImpl.view, payPanelUI.view);
		view.setViewListener(controller);
		controller.setView(view);
		employeeManagerUIImpl.setObservableCurrentDate(controller.getObservableCurrentDate());
		payPanelUI.setObservableCurrentDate(controller.getObservableCurrentDate());
		controller.setDefaultModelToView();
	}

}
