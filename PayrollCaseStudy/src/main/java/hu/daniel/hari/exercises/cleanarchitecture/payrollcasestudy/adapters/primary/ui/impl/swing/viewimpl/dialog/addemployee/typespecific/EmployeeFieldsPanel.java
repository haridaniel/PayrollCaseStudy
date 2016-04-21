package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific;

import javax.swing.border.EtchedBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;

public abstract class EmployeeFieldsPanel<T extends EmployeeViewModel> extends FieldsPanel {
	public abstract T getModel();
	public EmployeeFieldsPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
}
