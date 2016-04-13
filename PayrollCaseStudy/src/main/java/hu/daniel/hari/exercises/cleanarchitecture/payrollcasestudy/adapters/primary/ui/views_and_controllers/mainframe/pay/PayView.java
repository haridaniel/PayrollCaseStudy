package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;

public interface PayView extends HasListener<PayView.PayViewListener>,
	ModelConsumer<PayView.PayViewModel>
{

	public static interface PayViewListener {
		void onFulfillPayAction();
	}
	
	public static class PayViewModel {
		public boolean isFulfillButtonEnabled;
	}

}