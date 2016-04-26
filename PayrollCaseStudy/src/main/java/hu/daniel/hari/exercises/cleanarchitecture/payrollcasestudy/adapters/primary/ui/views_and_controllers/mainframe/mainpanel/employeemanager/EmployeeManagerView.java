package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;

public interface EmployeeManagerView extends
	ControlView<EmployeeManagerView.EmployeeManagerViewListener>,
	ModelConsumer<EmployeeManagerView.EmployeeManagerViewModel>
{

	public static interface EmployeeManagerViewListener {
		void onDeleteEmployeeAction();
		void onAddEmployeeAction();
		void onAddTimeCardAction();
	}
	
	public static class EmployeeManagerViewModel {
		public ButtonEnabledStates buttonsEnabledStates;
		public EmployeeManagerViewModel(ButtonEnabledStates buttonsEnabledStates) {
			this.buttonsEnabledStates = buttonsEnabledStates;
		}

		public static class ButtonEnabledStates {
			public boolean deleteEmployee;
			public boolean addTimeCard;
			public boolean addSalesReceipt;
			public boolean addServiceCharge;
		}
	}


}