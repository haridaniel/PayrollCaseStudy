package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelConsumer;

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