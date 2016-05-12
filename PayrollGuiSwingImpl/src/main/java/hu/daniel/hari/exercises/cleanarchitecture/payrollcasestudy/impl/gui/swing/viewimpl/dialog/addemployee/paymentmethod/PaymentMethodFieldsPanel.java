package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.paymentmethod;

import javax.swing.border.EtchedBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.FieldsPanel;

public abstract class PaymentMethodFieldsPanel<T extends PaymentMethod> extends FieldsPanel {
	public PaymentMethodFieldsPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	public abstract T getModel();
}
