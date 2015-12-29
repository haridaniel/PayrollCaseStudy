package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.table;

import java.util.List;

public class EmployeesTableViewModel {
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
