package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.field.IntegerField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;

public class SalariedEmployeeFieldsPanel extends EmployeeFieldsPanel<SalariedEmployeeViewModel>{
	public IntegerField monthlySalaryField = new IntegerField();
	
	public SalariedEmployeeFieldsPanel() {
		initFields();
	}

	private void initFields() {
		addField("Salary", monthlySalaryField);
		makeCompactGrid(); 
	}

	@Override
	public SalariedEmployeeViewModel getModel() {
		SalariedEmployeeViewModel salariedEmployeeViewModel = new SalariedEmployeeViewModel();
		salariedEmployeeViewModel.monthlySalary = monthlySalaryField.getInteger().orElse(null);
		return salariedEmployeeViewModel;
	}

}
