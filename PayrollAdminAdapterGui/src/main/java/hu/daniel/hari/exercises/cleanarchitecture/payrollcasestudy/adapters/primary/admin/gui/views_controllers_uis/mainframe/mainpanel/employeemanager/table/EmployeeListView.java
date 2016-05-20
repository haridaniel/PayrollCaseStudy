package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table;

import java.util.List;
import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.EmployeeViewItem;

public interface EmployeeListView extends
	ControlView<EmployeeListView.EmployeeListViewListener>,
	ModelConsumer<EmployeeListView.EmployeeListViewModel>
{

	public static interface EmployeeListViewListener {
		void onSelectionChanged(Optional<Integer> employeeIndex);
	}
	
	public static class EmployeeListViewModel {
		public List<EmployeeViewItem> employeeViewItems; 

		public EmployeeListViewModel(List<EmployeeViewItem> employeeViewItems) {
			this.employeeViewItems = employeeViewItems;
		}
		
	}

	
}