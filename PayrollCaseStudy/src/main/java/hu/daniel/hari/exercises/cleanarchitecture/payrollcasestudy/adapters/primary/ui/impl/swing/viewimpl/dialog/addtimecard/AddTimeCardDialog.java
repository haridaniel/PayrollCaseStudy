package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addtimecard;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.UIConstants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.OkCancelButtonBar;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.OkCancelButtonBar.OkCancelButtonBarListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.field.DateField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.field.IntegerField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.DefaultModalDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewListener;

public class AddTimeCardDialog extends DefaultModalDialog<AddTimeCardViewListener> implements AddTimeCardView {

	private final FieldsPanel fieldsPanel = new FieldsPanel();

	private JLabel employeeNameField = new JLabel();
	private DateField dateField = new DateField(UIConstants.DATE_FORMAT);
	private IntegerField hoursField = new IntegerField();
	
	private JLabel errorMessageLabel;
	
	public AddTimeCardDialog() {
		this(null);
	}
	public AddTimeCardDialog(JFrame parentFrame) {
		super(parentFrame, "Add TimeCard");
		initUI();
		initFields();
		centerParent();
		setFocusField(hoursField);
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
				errorMessageLabel = new JLabel("");
				errorMessageLabel.setForeground(Color.RED);
				panel.add(errorMessageLabel, BorderLayout.SOUTH);
			}
		}
		{
			OkCancelButtonBar okCancelButtonBar = new OkCancelButtonBar(new OkCancelButtonBarListener() {
				@Override
				public void onOk() {
					getListener().onAdd();
				}
				
				@Override
				public void onCancel() {
					getListener().onCancel();
				}
			}, "ADD", "CANCEL");
			getContentPane().add(okCancelButtonBar, BorderLayout.SOUTH);
		}
		
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

}
