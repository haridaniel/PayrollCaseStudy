package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.typespecific;

import javax.swing.border.EtchedBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.FieldsPanel;

public abstract class EmployeeFieldsPanel<T extends EmployeeViewModel> extends FieldsPanel {
	public EmployeeFieldsPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	public abstract T getModel();
}
