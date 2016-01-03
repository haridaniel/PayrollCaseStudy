package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table;

import java.util.List;
import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.ModelConsumer;

public interface EmployeesTableView extends 
	HasListener<EmployeesTableView.EmployeesTableViewListener>,
	ModelConsumer<EmployeesTableView.EmployeesTableViewModel>
{

	public static interface EmployeesTableViewListener {
		void onLoaded();
		void onSelectionChanged(Optional<Integer> employeeId);
	}
	
	public static class EmployeesTableViewModel {
		public List<EmployeeViewItem> employeeViewItems; 

		public EmployeesTableViewModel(List<EmployeeViewItem> employeeViewItems) {
			this.employeeViewItems = employeeViewItems;
		}

		public static class EmployeeViewItem {
			public int id;
			public String name;
			public String address;
			public String paymentClassificationType;
		}
		
	}

	
}