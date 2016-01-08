package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist.PayCheckListView;

public interface PayDayView extends HasListener<PayDayView.PayDayViewListener> {

	PayCheckListView getPayCheckListView();

	public static interface PayDayViewListener {

		void onSendPayAction();
		void onInitialized();
	}


}