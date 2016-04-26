package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist.PayListUI;

public class PayUI<V extends PayView> extends
	UI<V, PayController>
{

	private PayListUI<?> payListUI;

	public PayUI(
			PayController controller,
			PayListUI<?> payListUI
			) {
		super(controller);
		this.payListUI = payListUI;
		payListUI.getObservablePayListState().addChangeListener(controller);
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
		payListUI.setObservableCurrentDate(observableCurrentDate);
	}

}
