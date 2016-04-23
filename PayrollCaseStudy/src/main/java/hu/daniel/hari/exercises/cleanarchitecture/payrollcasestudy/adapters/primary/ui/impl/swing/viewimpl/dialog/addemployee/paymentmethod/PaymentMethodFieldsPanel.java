package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.paymentmethod;

import javax.swing.border.EtchedBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymentMethod;

public abstract class PaymentMethodFieldsPanel<T extends PaymentMethod> extends FieldsPanel {
	public PaymentMethodFieldsPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	public abstract T getModel();
}
