package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.paymentmethod;

import javax.swing.JTextField;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel.DirectPaymentMethod;

public class DirectPaymentMethodFieldsPanel extends PaymentMethodFieldsPanel<DirectPaymentMethod> {
	private JTextField tfAccountNumber = new JTextField();

	public DirectPaymentMethodFieldsPanel() {
		initFields();
	}
	
	protected void initFields() {
		addField("Account number", tfAccountNumber);
	}
	
	@Override
	public DirectPaymentMethod getModel() {
		return new DirectPaymentMethod(tfAccountNumber.getText());
	}

}
