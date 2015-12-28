package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

import javax.swing.table.TableModel;

public class EmployeesOverviewPanelViewModel {
	public TableModel tableModel;
	
	public EmployeesOverviewPanelViewModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}
}
