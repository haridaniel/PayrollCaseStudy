package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addunionmemberaffiliation;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberView.AddUnionMemberViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.OkCancelButtonBar;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.ValidationErrorMessagesLabel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.OkCancelButtonBar.OkCancelButtonBarListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field.IntegerField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.DefaultModalDialog;

public class AddUnionMemberDialog extends 
	DefaultModalDialog<AddUnionMemberViewListener> implements
	AddUnionMemberView, 
	OkCancelButtonBarListener
{
	private final FieldsPanel fieldsPanel = new FieldsPanel();

	private JLabel employeeNameField = new JLabel();
	private IntegerField unionMemberIdField = new IntegerField();
	private IntegerField weeklyDueAmountField = new IntegerField();
	
	private ValidationErrorMessagesLabel validationErrorMessagesLabel;

	public AddUnionMemberDialog(JFrame parentFrame) {
		super(parentFrame, "Add Union Member");
		initUI();
		initFields();
		setFocus(unionMemberIdField);
	}

	@Override
	public void setModel(AddUnionMemberViewInputModel viewModel) {
		employeeNameField.setText(viewModel.employeeName);
	}

	@Override
	public AddUnionMemberViewOutputModel getModel() {
		AddUnionMemberViewOutputModel outputModel = new AddUnionMemberViewOutputModel();
		outputModel.unionMemberId = unionMemberIdField.getInteger();
		outputModel.weeklyDueAmount = weeklyDueAmountField.getInteger();
		return outputModel;
	}

	@Override
	public void onOk() {
		getViewListener().onAdd();
	}

	@Override
	public void onCancel() {
		getViewListener().onCancel();
	}

	@Override
	public void setValidationErrorMessagesModel(ValidationErrorMessagesModel errorMessagesModel) {
		validationErrorMessagesLabel.setMessages(errorMessagesModel.validationErrorMessages);
	}

	private void initFields() {
		fieldsPanel.addField("Employee", employeeNameField);
		fieldsPanel.addField("Union Member Id", unionMemberIdField);
		fieldsPanel.addField("Weekly due amount", weeklyDueAmountField);
	}

	private void initUI() {
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			JPanel panel_1 = new JPanel();
			panel.add(panel_1, BorderLayout.NORTH);
			
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(fieldsPanel, BorderLayout.NORTH);
			
			{
				validationErrorMessagesLabel = new ValidationErrorMessagesLabel();
				panel.add(validationErrorMessagesLabel, BorderLayout.SOUTH);
			}
		}
		{
			OkCancelButtonBar okCancelButtonBar = new OkCancelButtonBar(this, "ADD", "CANCEL");
			getContentPane().add(okCancelButtonBar, BorderLayout.SOUTH);
		}
		
	}
	
}
