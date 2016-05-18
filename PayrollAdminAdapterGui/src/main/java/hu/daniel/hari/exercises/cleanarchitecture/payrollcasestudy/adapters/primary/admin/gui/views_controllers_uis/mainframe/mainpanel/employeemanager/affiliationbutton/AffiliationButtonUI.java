package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.affiliationbutton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.ObservableSelectedEployeeItem;

public class AffiliationButtonUI<V extends AffiliationButtonView> extends 
	UI<V, AffiliationButtonController>
{

	public AffiliationButtonUI(
			AffiliationButtonController controller, 
			V view
			) {
		super(controller, view);
	}

	public void setObservableSelectedEmployee(ObservableSelectedEployeeItem observableSelectedEployee) {
		controller.setObservableSelectedEmployee(observableSelectedEployee);
	}

}
