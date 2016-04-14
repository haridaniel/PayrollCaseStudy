package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;

public class SalariedEmployeeFieldsPanel extends FieldsPanel implements EmployeeFieldsPanel<SalariedEmployeeViewModel>{
	public JFormattedTextField tfMonthlySalary = new JFormattedTextField(NumberFormat.getIntegerInstance());
	
	public SalariedEmployeeFieldsPanel() {
		initFields();
	}

	private void initFields() {
		addField("Salary", tfMonthlySalary);
		makeCompactGrid(); 
	}

	@Override
	public SalariedEmployeeViewModel getModel() {
		SalariedEmployeeViewModel salariedEmployeeViewModel = new SalariedEmployeeViewModel();
		salariedEmployeeViewModel.monthlySalary = getIntegerOrNull(tfMonthlySalary);
		return salariedEmployeeViewModel;
	}
	
	private Integer getIntegerOrNull(JFormattedTextField textField) {
		return textField.getValue() == null? null : Integer.parseInt(textField.getValue().toString());
	}
}
