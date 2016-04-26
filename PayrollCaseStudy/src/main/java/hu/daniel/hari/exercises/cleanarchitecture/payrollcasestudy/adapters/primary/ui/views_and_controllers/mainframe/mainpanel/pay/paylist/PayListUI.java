package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.pay.paylist;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public abstract class PayListUI<V extends PayListView> extends 
	UI<V, PayListController> 
{

	public PayListUI(
			PayListController controller,
			V view
			) {
		super(controller, view);
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
	}
	
	public ObservableValue<PayListState> getObservablePayListState() {
		return controller.getObservablePayListState();
	}

}
