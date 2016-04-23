package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.field.IntegerField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.CommissionedEmployeeViewModel;

public class CommissionedEmployeeFieldsPanel extends EmployeeFieldsPanel<CommissionedEmployeeViewModel>{
	public IntegerField biWeeklyBaseSalaryField = new IntegerField();
	public IntegerField commissionRatePercentageField = new IntegerField();

	public CommissionedEmployeeFieldsPanel() {
		initFields();
	}
	
	private void initFields() {
		addField("Bi-weekly salary", biWeeklyBaseSalaryField);
		addField("Commission rate %", commissionRatePercentageField);
	}

	@Override
	public CommissionedEmployeeViewModel getModel() {
		CommissionedEmployeeViewModel model = new CommissionedEmployeeViewModel();
		model.biWeeklyBaseSalary = biWeeklyBaseSalaryField.getInteger().orElse(null);
		model.commissionRatePercentage = commissionRatePercentageField.getInteger().orElse(null);
		return model;
	}
	
}
