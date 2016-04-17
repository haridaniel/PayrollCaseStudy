package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;

public class HourlyEmployeeFieldsPanel extends FieldsPanel implements EmployeeFieldsPanel<HourlyEmployeeViewModel>{
	public JFormattedTextField tfHourlyWage = new JFormattedTextField(NumberFormat.getIntegerInstance());
	
	public HourlyEmployeeFieldsPanel() {
		initFields();
	}

	private void initFields() {
		addField("Hourly wage", tfHourlyWage);
		makeCompactGrid(); 
	}

	@Override
	public HourlyEmployeeViewModel getModel() {
		HourlyEmployeeViewModel hourlyEmployeeViewModel = new HourlyEmployeeViewModel();
		hourlyEmployeeViewModel.hourlyWage = getIntegerOrNull(tfHourlyWage);
		return hourlyEmployeeViewModel;
	}

	private Integer getIntegerOrNull(JFormattedTextField textField) {
		return textField.getValue() == null? null : Integer.parseInt(textField.getValue().toString());
	}
	
}
