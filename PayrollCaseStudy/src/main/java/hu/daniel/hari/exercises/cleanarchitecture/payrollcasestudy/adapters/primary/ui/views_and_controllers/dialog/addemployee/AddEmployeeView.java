package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.ClosableView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;

public interface AddEmployeeView extends ClosableView<AddEmployeeView.AddEmployeeViewListener>,
	ModelProducer<AddEmployeeView.AddEmployeeViewModel>
{
	
	public interface AddEmployeeViewListener extends CloseableViewListener {
		void onAddEmployee();
		void onCancel();
	}

	public class AddEmployeeViewModel {
		public int employeeId;
		public String name;
		public String address;
	}

}