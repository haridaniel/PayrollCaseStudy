package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.util.List;
import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;

public interface EmployeeListView extends 
	HasListener<EmployeeListView.EmployeeListViewListener>,
	ModelConsumer<EmployeeListView.EmployeeListViewModel>
{

	public static interface EmployeeListViewListener {
		void onSelectionChanged(Optional<Integer> employeeId);
	}
	
	public static class EmployeeListViewModel {
		public List<EmployeeViewItem> employeeViewItems; 

		public EmployeeListViewModel(List<EmployeeViewItem> employeeViewItems) {
			this.employeeViewItems = employeeViewItems;
		}

		public static class EmployeeViewItem {
			public int id;
			public String name;
			public String address;
			public String waging;
			public String nextPayDay;
		}
		
	}

	
}