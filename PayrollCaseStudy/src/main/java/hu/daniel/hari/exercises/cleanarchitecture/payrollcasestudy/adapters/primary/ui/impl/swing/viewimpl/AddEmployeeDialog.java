package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.util.SpringUtilities;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeView;

public class AddEmployeeDialog extends JDialog implements AddEmployeeView {

	private final JPanel fieldsPanel = new JPanel();
	private AddEmployeeDialogListener listener;
	
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
	public void setListener(AddEmployeeDialogListener listener) {
		this.listener = listener;
	}

	@Override
	public AddEmployeeViewModel getModel() {
		AddEmployeeViewModel viewModel = new AddEmployeeViewModel();
		viewModel.employeeId = Integer.parseInt(tfEmployeeId.getText());
		viewModel.name = tfName.getText();
		viewModel.address = tfAddress.getText();
		return viewModel;
	}

	@Override
	public void close() {
		dispose();
	}

	private void initUI() {
		setTitle("Add Employee");
		setModal(true);
		
		setSize(450, 300);
		
		getContentPane().setLayout(new BorderLayout());
		fieldsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(fieldsPanel, BorderLayout.NORTH);
		fieldsPanel.setLayout(new SpringLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SAVE");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listener.onAddEmployee();
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
						listener.onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}

}
