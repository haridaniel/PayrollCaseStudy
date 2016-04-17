package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.common.base.Joiner;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.DefaultModalDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific.EmployeeFieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific.HourlyEmployeeFieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific.SalariedEmployeeFieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.OkCancelButtonBar;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.ValidationErrorMessagesLabel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.OkCancelButtonBar.OkCancelButtonBarListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.field.IntegerField;

public class AddEmployeeDialog extends DefaultModalDialog<AddEmployeeViewListener> implements AddEmployeeView {

	private final FieldsPanel fieldsPanel = new FieldsPanel();
	private ValidationErrorMessagesLabel errorMessageLabel;
	
	private IntegerField ifEmployeeId = new IntegerField();
	private JTextField tfName = new JTextField();
	private JTextField tfAddress = new JTextField();
	private JComboBox<EmployeeType> cbEmployeeType = new JComboBox<>();
	
	private SalariedEmployeeFieldsPanel salariedEmployeeFieldsPanel = new SalariedEmployeeFieldsPanel();
	private HourlyEmployeeFieldsPanel hourlyEmployeeFieldsPanel = new HourlyEmployeeFieldsPanel();
	
	private enum EmployeeType {
		SALARIED,
		HOURLY
	}
	private final Map<EmployeeType, EmployeeFieldsPanel<?>> employeeFieldsPanelByEmployeeType = new HashMap<EmployeeType, EmployeeFieldsPanel<?>>() {{
		put(EmployeeType.SALARIED, salariedEmployeeFieldsPanel);
		put(EmployeeType.HOURLY, hourlyEmployeeFieldsPanel);
	}};
	private EmployeeFieldsPanel<? extends EmployeeViewModel> currentEmployeeFieldsPanel;

	public AddEmployeeDialog() {
		this(null);
	}
	public AddEmployeeDialog(JFrame parentFrame) {
		super(parentFrame, "Add Employee");
		initUI();
		initCommonFields();
		populateCbEmployeeType();
		centerParent();
		initListeners();
		initDefaults();
	}
	
	private void initDefaults() {
		cbEmployeeType.setSelectedIndex(0);
	}
	
	private void initListeners() {
		cbEmployeeType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentEmployeeFieldsPanel = employeeFieldsPanelByEmployeeType.get((EmployeeType) cbEmployeeType.getSelectedItem());
				updateTypeSpecificPanelsVisibility();
			}

		});
	}
	
	private void updateTypeSpecificPanelsVisibility() {
		salariedEmployeeFieldsPanel.setVisible(salariedEmployeeFieldsPanel == currentEmployeeFieldsPanel);
		hourlyEmployeeFieldsPanel.setVisible(hourlyEmployeeFieldsPanel == currentEmployeeFieldsPanel);
	}
	
	private void populateCbEmployeeType() {
		cbEmployeeType.setModel(new DefaultComboBoxModel<>(EmployeeType.values()));
	}

	private void initCommonFields() {
		fieldsPanel.addField("Id", ifEmployeeId);
		fieldsPanel.addField("Name", tfName);
		fieldsPanel.addField("Address", tfAddress);
		fieldsPanel.addField("Type", cbEmployeeType);
		fieldsPanel.makeCompactGrid(); 
	}

	@Override
	public EmployeeViewModel getModel() {
		return fillBase(currentEmployeeFieldsPanel.getModel());
	}
	private EmployeeViewModel fillBase(EmployeeViewModel viewModel) {
		viewModel.employeeId = ifEmployeeId.getInteger();
		viewModel.name = tfName.getText();
		viewModel.address = tfAddress.getText();
		return viewModel;
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
			
			JPanel typeSpecificFieldsPanel = new JPanel();
			panel_1.add(typeSpecificFieldsPanel, BorderLayout.SOUTH);
			typeSpecificFieldsPanel.setLayout(new BoxLayout(typeSpecificFieldsPanel, BoxLayout.Y_AXIS));
			typeSpecificFieldsPanel.add(salariedEmployeeFieldsPanel);
			typeSpecificFieldsPanel.add(hourlyEmployeeFieldsPanel);
			
			{
				errorMessageLabel = new ValidationErrorMessagesLabel();
				panel.add(errorMessageLabel, BorderLayout.SOUTH);
			}
		}
		{
			OkCancelButtonBar okCancelButtonBar = new OkCancelButtonBar(new OkCancelButtonBarListener() {
				@Override
				public void onOk() {
					getListener().onAddEmployee();
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
	public void setModel(ValidationErrorMessagesModel viewModel) {
		errorMessageLabel.setMessages(viewModel.validationErrorMessages);
	}

}
