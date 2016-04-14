package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;

public interface EmployeeFieldsPanel<T extends EmployeeViewModel> {
	T getModel();
}
