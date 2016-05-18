package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymasterPaymentMethod;

public class PaymasterPaymentMethodFieldsPanel extends PaymentMethodFieldsPanel<PaymasterPaymentMethod> {

	public PaymasterPaymentMethodFieldsPanel() {
		setBorder(null);
	}
	@Override
	public PaymasterPaymentMethod getModel() {
		return new PaymasterPaymentMethod();
	}

}
