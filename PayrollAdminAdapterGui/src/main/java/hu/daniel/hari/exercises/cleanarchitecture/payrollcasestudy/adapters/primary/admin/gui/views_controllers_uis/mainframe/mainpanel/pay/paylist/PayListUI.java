package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay.paylist;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;

public abstract class PayListUI<V extends PayListView> extends 
	UI<V, PayListController> 
{

	public PayListUI(
			PayListController controller,
			V view
			) {
		super(controller, view);
	}

	public void setObservableCurrentDate(Observable<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
	}
	
	public Observable<PayListState> getObservablePayListState() {
		return controller.getObservablePayListState();
	}

}
