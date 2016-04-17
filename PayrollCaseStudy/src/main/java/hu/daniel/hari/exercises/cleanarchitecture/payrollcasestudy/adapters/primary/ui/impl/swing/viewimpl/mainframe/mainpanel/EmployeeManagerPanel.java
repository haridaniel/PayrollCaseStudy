package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewModel.ButtonEnabledStates;

public class EmployeeManagerPanel extends JPanel implements EmployeeManagerView {
	private JPanel tableHolder;
	private JButton btDeleteEmployee;
	private JButton btAddTimeCard;
	private JButton btAddSalesReceipt;
	private JButton btAddServiceCharge;

	private EmployeeManagerViewListener listener;

	public EmployeeManagerPanel(EmployeeListPanel employeeListPanel) {
		initUI();
		tableHolder.add(employeeListPanel);
	}

	@Override
	public void setListener(EmployeeManagerViewListener listener) {
		this.listener = listener;
	}

	private void onDeleteEmployeeAction() {
		listener.onDeleteEmployeeAction();
	}

	private void onAddEmployeeAction() {
		listener.onAddEmployeeAction();
	}

	private void onAddTimeCardAction() {
		listener.onAddTimeCardAction();
	}

	private void onAddSalesReceiptAction() {
		// TODO Auto-generated method stub
		
	}

	private void onAddServiceChargeAction() {
		// TODO Auto-generated method stub
		
	}

	private void initUI() {

		setLayout(new BorderLayout(0, 0));

		tableHolder = new JPanel();
		tableHolder.setLayout(new BorderLayout(0, 0));
		add(tableHolder, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.EAST);

		btDeleteEmployee = new JButton("Delete");
		btDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDeleteEmployeeAction();
			}
		});

		JButton btAddEmployee = new JButton("Add..");
		btAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddEmployeeAction();
			}
		});

		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(
				gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btAddEmployee, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
								.addComponent(btDeleteEmployee, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_buttonPanel.setVerticalGroup(
				gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(btAddEmployee)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btDeleteEmployee)
						.addGap(237))
				);
		buttonPanel.setLayout(gl_buttonPanel);
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		btAddTimeCard = new JButton("Add time card..");
		btAddTimeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddTimeCardAction();
			}
		});
		panel.add(btAddTimeCard);

		btAddSalesReceipt = new JButton("Add sales receipt..");
		btAddSalesReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddSalesReceiptAction();
			}
		});
		panel.add(btAddSalesReceipt);

		btAddServiceCharge = new JButton("Add Service charge..");
		btAddServiceCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddServiceChargeAction();
			}

		});
		panel.add(btAddServiceCharge);

	}
	
	@Override
	public void setModel(EmployeeManagerViewModel viewModel) {
		setButtonsEnabled(viewModel.buttonsEnabledStates);
	}

	private void setButtonsEnabled(ButtonEnabledStates states) {
		btDeleteEmployee.setEnabled(states.deleteEmployee);
		btAddTimeCard.setEnabled(states.addTimeCard);
		btAddSalesReceipt.setEnabled(states.addSalesReceipt);
		btAddServiceCharge.setEnabled(states.addServiceCharge);
	}
}
