package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.EmployeeManagerUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.PayUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelUI;

public class MainPanelUIImpl extends
	MainPanelUI<MainPanel>
{

	@Inject
	public MainPanelUIImpl(
			MainPanelController controller,
			EmployeeManagerUIImpl employeeManagerUIImpl,
			PayUIImpl payUIImpl
			) {
		super(controller, employeeManagerUIImpl, payUIImpl);
		setView(new MainPanel(employeeManagerUIImpl.view, payUIImpl.view));
	}

}
