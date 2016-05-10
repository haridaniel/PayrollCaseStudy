package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ModelConsumer;

public interface PayView extends
	ControlView<PayView.PayViewListener>,
	ModelConsumer<PayView.PayViewModel>
{

	public static interface PayViewListener {
		void onFulfillPayAction();
	}
	
	public static class PayViewModel {
		public boolean isFulfillButtonEnabled;
	}

}