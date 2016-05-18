package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay.paylist.PayListUI;

public abstract class PayUI<V extends PayView> extends
	UI<V, PayController>
{

	private PayListUI<?> payListUI;

	public PayUI(
			PayController controller,
			V view,
			PayListUI<?> payListUI
			) {
		super(controller, view);
		this.payListUI = payListUI;
		controller.setObservablePayListState(payListUI.getObservablePayListState());
	}

	public void setObservableCurrentDate(Observable<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
		payListUI.setObservableCurrentDate(observableCurrentDate);
	}

}
