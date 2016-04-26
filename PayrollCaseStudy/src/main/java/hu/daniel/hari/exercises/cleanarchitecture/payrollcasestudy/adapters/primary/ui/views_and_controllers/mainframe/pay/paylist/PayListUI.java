package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;

public class PayListUI<V extends PayListView> extends UI<V, PayListController> {

	public PayListUI(
			PayListController controller
			) {
		super(controller);
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
	}
	
	public ObservableValue<PayListState> getObservablePayListState() {
		return controller.getObservablePayListState();
	}

}
