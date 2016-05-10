package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addtimecard;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.UIImplConstants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.OkCancelButtonBar;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.ValidationErrorMessagesLabel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite.OkCancelButtonBar.OkCancelButtonBarListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field.DateField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field.IntegerField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.DefaultModalDialog;

public class AddTimeCardDialog extends DefaultModalDialog<AddTimeCardViewListener> implements AddTimeCardView, OkCancelButtonBarListener {

	private final FieldsPanel fieldsPanel = new FieldsPanel();

	private JLabel employeeNameField = new JLabel();
	private DateField dateField = new DateField(UIImplConstants.DATE_FORMAT);
	private IntegerField hoursField = new IntegerField();
	
	private ValidationErrorMessagesLabel validationErrorMessagesLabel;
	
	public AddTimeCardDialog() {
		this(null);
	}
	public AddTimeCardDialog(JFrame parentFrame) {
		super(parentFrame, "Add TimeCard");
		initUI();
		initFields();
		setFocus(hoursField);
	}
	
	@Override
	public void setModel(AddTimeCardViewInputModel viewModel) {
		dateField.setDate(viewModel.defaultDate);
		employeeNameField.setText(viewModel.employeeName);
	}
	@Override
	public AddTimeCardViewOutputModel getModel() {
		AddTimeCardViewOutputModel outputModel = new AddTimeCardViewOutputModel();
		outputModel.date = dateField.getDate();
		outputModel.workingHourQty = hoursField.getInteger();
		return outputModel;
	}
	@Override
	public void setValidationErrorMessagesModel(ValidationErrorMessagesModel errorMessagesModel) {
		validationErrorMessagesLabel.setMessages(errorMessagesModel.validationErrorMessages);
	}
	@Override
	public void onOk() {
		getViewListener().onAdd();
	}
	@Override
	public void onCancel() {
		getViewListener().onCancel();
	}
	private void initFields() {
		fieldsPanel.addField("Employee", employeeNameField);
		fieldsPanel.addField("Date", dateField);
		fieldsPanel.addField("Working hours", hoursField);
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
