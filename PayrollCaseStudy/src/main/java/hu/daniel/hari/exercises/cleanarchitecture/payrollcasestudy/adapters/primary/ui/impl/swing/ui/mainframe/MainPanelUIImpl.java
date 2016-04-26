package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.EmployeeManagerUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.PayUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.MainPanelController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.MainPanelUI;

public class MainPanelUIImpl extends
	MainPanelUI<MainPanel>
{

	private EmployeeManagerUIImpl employeeManagerUIImpl;
	private PayUIImpl payUIImpl;

	@Inject
	public MainPanelUIImpl(
			MainPanelController controller,
			EmployeeManagerUIImpl employeeManagerUIImpl,
			PayUIImpl payUIImpl
			) {
		super(controller, employeeManagerUIImpl, payUIImpl);
		this.employeeManagerUIImpl = employeeManagerUIImpl;
		this.payUIImpl = payUIImpl;
	}

	@Override
	protected MainPanel createView() {
		return new MainPanel(employeeManagerUIImpl.getView(), payUIImpl.getView());
	}

}
