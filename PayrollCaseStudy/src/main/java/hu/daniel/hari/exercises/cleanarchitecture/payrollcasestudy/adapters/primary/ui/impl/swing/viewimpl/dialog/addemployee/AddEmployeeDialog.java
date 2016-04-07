package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.google.common.base.Joiner;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.DefaultDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.util.SpringUtilities;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;

public class AddEmployeeDialog extends DefaultDialog<AddEmployeeViewListener> implements AddEmployeeView {

	private final JPanel fieldsPanel = new JPanel();
	private JLabel errorMessageLabel;
	
	private JTextField tfEmployeeId = new JTextField();
	private JTextField tfName = new JTextField();
	private JTextField tfAddress = new JTextField();

	public AddEmployeeDialog(JFrame parentFrame) {
		super(parentFrame);
		initUI();
		initFields();
		centerParent();
	}
	
	private void centerParent() {
		//Not working... fuck swing
		setLocationRelativeTo(getParent());
	}
	
	private void initFields() {
		addField("Id", tfEmployeeId);
		addField("Name", tfName);
		addField("Address", tfAddress);
		layoutFieldsPanel(3);
	}

	private void addField(String labelText, JTextField jTextField) {
		JLabel jLabel = new JLabel(labelText, JLabel.TRAILING);
		jLabel.setLabelFor(jTextField);
		fieldsPanel.add(jLabel);
		fieldsPanel.add(jTextField);
	}
	
	private void layoutFieldsPanel(int numFields) {
		SpringUtilities.makeCompactGrid(fieldsPanel,
		                                numFields , 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
	}


	@Override
	public AddEmployeeViewModel getModel() {
		AddEmployeeViewModel viewModel = new AddEmployeeViewModel();
		viewModel.employeeId = Integer.parseInt(tfEmployeeId.getText());
		viewModel.name = tfName.getText();
		viewModel.address = tfAddress.getText();
		return viewModel;
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
			{
				errorMessageLabel = new JLabel("");
				errorMessageLabel.setForeground(Color.RED);
				panel.add(errorMessageLabel, BorderLayout.SOUTH);
			}
			fieldsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.add(fieldsPanel, BorderLayout.CENTER);
			fieldsPanel.setLayout(new SpringLayout());
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
