package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.ObservableSelectedEployeeItem;

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
