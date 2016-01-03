package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.ModelProducer;

public interface AddEmployeeView extends 
	HasListener<AddEmployeeView.AddEmployeeDialogListener>,
	ModelProducer<AddEmployeeView.AddEmployeeViewModel>
{

	void close();
	
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