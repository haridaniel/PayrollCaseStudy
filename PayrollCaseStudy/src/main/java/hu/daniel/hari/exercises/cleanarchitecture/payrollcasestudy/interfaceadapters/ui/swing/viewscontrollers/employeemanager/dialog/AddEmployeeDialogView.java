package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.util.SpringUtilities;

import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEmployeeDialogView extends JDialog {

	private final JPanel fieldsPanel = new JPanel();
	private AddEmployeeDialogListener listener;
	
	private JTextField tfEmployeeId = new JTextField();
	private JTextField tfName = new JTextField();
	private JTextField tfAddress = new JTextField();
	
	public AddEmployeeDialogView() {
		super();
		initUI();
		initFields();
	}
	
	private void initFields() {
		addField("Id", tfEmployeeId);
		addField("Name", tfName);
		addField("Address", tfAddress);
		layoutFieldPanel(3);
	}

	private void layoutFieldPanel(int numFields) {
		SpringUtilities.makeCompactGrid(fieldsPanel,
		                                numFields , 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
	}

	private void addField(String labelText, JTextField jTextField) {
		JLabel jLabel = new JLabel(labelText, JLabel.TRAILING);
		jLabel.setLabelFor(jTextField);
		fieldsPanel.add(jLabel);
		fieldsPanel.add(jTextField);
	}

	public void setListener(AddEmployeeDialogListener listener) {
		this.listener = listener;
	}

	private void initUI() {
		setTitle("Add Employee");
		setLocationRelativeTo(getParent());
		setModal(true);
		
		setBounds(100, 100, 450, 300);
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
	
	public interface AddEmployeeDialogListener {
		void onAddEmployee();
		void onCancel();
	}

	public AddEmployeeDialogViewModel getModel() {
		AddEmployeeDialogViewModel viewModel = new AddEmployeeDialogViewModel();
		viewModel.employeeId = Integer.parseInt(tfEmployeeId.getText());
		viewModel.name = tfName.getText();
		viewModel.address = tfAddress.getText();
		return viewModel;
	}

}
