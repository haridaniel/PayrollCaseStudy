package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListUI;

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

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
		payListUI.setObservableCurrentDate(observableCurrentDate);
	}

}
