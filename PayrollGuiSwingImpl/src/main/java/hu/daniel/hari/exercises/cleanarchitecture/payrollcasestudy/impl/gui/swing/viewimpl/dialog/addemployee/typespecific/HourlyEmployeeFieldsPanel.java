package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.typespecific;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field.IntegerField;

public class HourlyEmployeeFieldsPanel extends EmployeeFieldsPanel<HourlyEmployeeViewModel>{
	public IntegerField hourlyWageField = new IntegerField();
	
	public HourlyEmployeeFieldsPanel() {
		initFields();
	}
	
	private void initFields() {
		addField("Hourly wage", hourlyWageField);
	}

	@Override
	public HourlyEmployeeViewModel getModel() {
		HourlyEmployeeViewModel hourlyEmployeeViewModel = new HourlyEmployeeViewModel();
		hourlyEmployeeViewModel.hourlyWage = hourlyWageField.getInteger().orElse(null);
		return hourlyEmployeeViewModel;
	}

}
