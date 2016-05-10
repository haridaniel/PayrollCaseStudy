package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.dialog.addemployee.paymentmethod;

import javax.swing.border.EtchedBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.component.composite.FieldsPanel;

public abstract class PaymentMethodFieldsPanel<T extends PaymentMethod> extends FieldsPanel {
	public PaymentMethodFieldsPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	public abstract T getModel();
}
