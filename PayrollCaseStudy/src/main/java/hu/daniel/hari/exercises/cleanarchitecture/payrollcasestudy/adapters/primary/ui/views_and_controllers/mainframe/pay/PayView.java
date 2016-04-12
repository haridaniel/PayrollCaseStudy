package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;

public interface PayView extends HasListener<PayView.PayViewListener> {

	public static interface PayViewListener {
		void onFulfillPayAction();
	}


}