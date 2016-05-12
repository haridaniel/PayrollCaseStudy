package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.typespecific;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field.IntegerField;

public class SalariedEmployeeFieldsPanel extends EmployeeFieldsPanel<SalariedEmployeeViewModel>{
	public IntegerField monthlySalaryField = new IntegerField();

	public SalariedEmployeeFieldsPanel() {
		initFields();
	}
	
	private void initFields() {
		addField("Salary", monthlySalaryField);
	}

	@Override
	public SalariedEmployeeViewModel getModel() {
		SalariedEmployeeViewModel salariedEmployeeViewModel = new SalariedEmployeeViewModel();
		salariedEmployeeViewModel.monthlySalary = monthlySalaryField.getInteger().orElse(null);
		return salariedEmployeeViewModel;
	}

}
