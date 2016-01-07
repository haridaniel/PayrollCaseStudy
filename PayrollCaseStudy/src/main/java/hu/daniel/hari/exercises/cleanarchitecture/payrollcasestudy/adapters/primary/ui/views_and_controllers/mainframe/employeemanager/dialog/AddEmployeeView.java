package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.Closeable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelProducer;

public interface AddEmployeeView extends 
	HasListener<AddEmployeeView.AddEmployeeDialogListener>,
	ModelProducer<AddEmployeeView.AddEmployeeViewModel>,
	Closeable
{
	
	public interface AddEmployeeDialogListener {
		void onAddEmployee();
		void onCancel();
	}

	public class AddEmployeeViewModel {
		public int employeeId;
		public String name;
		public String address;
	}

}