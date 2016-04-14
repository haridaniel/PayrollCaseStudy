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

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.FieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.DefaultDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific.EmployeeFieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific.HourlyEmployeeFieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.typespecific.SalariedEmployeeFieldsPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;

public class AddEmployeeDialog extends DefaultDialog<AddEmployeeViewListener> implements AddEmployeeView {

	private final FieldsPanel fieldsPanel = new FieldsPanel();
	private JLabel errorMessageLabel;
	
	private JFormattedTextField tfEmployeeId = new JFormattedTextField(NumberFormat.getIntegerInstance());
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
		super(parentFrame);
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

	private void centerParent() {
		//Not working... fuck swing
		setLocationRelativeTo(getParent());
	}
	
	private void initCommonFields() {
		fieldsPanel.addField("Id", tfEmployeeId);
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
		viewModel.employeeId = getIntegerOrNull(tfEmployeeId);
		viewModel.name = tfName.getText();
		viewModel.address = tfAddress.getText();
		return viewModel;
	}

	private Integer getIntegerOrNull(JFormattedTextField textField) {
		return textField.getValue() == null? null : Integer.parseInt(textField.getValue().toString());
	}

	private void initUI() {
		setTitle("Add Employee");
		setModal(true);
		
		setSize(450, 300);
		
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
				errorMessageLabel = new JLabel("");
				errorMessageLabel.setForeground(Color.RED);
				panel.add(errorMessageLabel, BorderLayout.SOUTH);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SAVE");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getListener().onAddEmployee();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getListener().onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	@Override
	public void setModel(AddEmployeeValidationErrorsModel viewModel) {
		errorMessageLabel.setText(toListAsHtml(viewModel.useCaseValidationErrorMessages));
	}

	private String toListAsHtml(List<String> strings) {
		return "<html>" + Joiner.on("<br/>").join(strings) + "</html>";
	}

}
