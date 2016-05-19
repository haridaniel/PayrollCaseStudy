package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.EmployeeManagerView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.EmployeeManagerView.EmployeeManagerViewModel.ButtonEnabledStates;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.employeemanager.affiliation.AffiliationButtonViewImpl;

public class EmployeeManagerPanel extends JPanel implements EmployeeManagerView {
	private JPanel bottomButtonPanel;
	private JPanel tableHolder;
	
	private JButton btDeleteEmployee;
	private JButton btAddTimeCard;
	private JButton btAddSalesReceipt;
	private JButton btAddServiceCharge;

	private EmployeeManagerViewListener listener;

	public EmployeeManagerPanel(
			EmployeeListPanel employeeListPanel,
			AffiliationButtonViewImpl affiliationButton
			) {
		initUI(affiliationButton);
		tableHolder.add(employeeListPanel);
	}

	@Override
	public void setViewListener(EmployeeManagerViewListener listener) {
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
		listener.onAddSalesReceiptAction();
	}

	private void onAddServiceChargeAction() {
		listener.onAddServiceChargeAction();
	}

	private void initUI(AffiliationButtonViewImpl affiliationButton) {

		setLayout(new BorderLayout(0, 0));

		tableHolder = new JPanel();
		tableHolder.setLayout(new BorderLayout(0, 0));
		add(tableHolder, BorderLayout.CENTER);

		JPanel rigthButtonPanel = new JPanel();
		add(rigthButtonPanel, BorderLayout.EAST);

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

		GroupLayout gl_buttonPanel = new GroupLayout(rigthButtonPanel);
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
		rigthButtonPanel.setLayout(gl_buttonPanel);
		
		bottomButtonPanel = new JPanel();
		add(bottomButtonPanel, BorderLayout.SOUTH);
		bottomButtonPanel.setLayout(new FlowLayout());

		btAddTimeCard = new JButton("Add time card..");
		btAddTimeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddTimeCardAction();
			}
		});
		bottomButtonPanel.add(btAddTimeCard);

		btAddSalesReceipt = new JButton("Add sales receipt..");
		btAddSalesReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddSalesReceiptAction();
			}
		});
		bottomButtonPanel.add(btAddSalesReceipt);

		btAddServiceCharge = new JButton("Add Service charge..");
		btAddServiceCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddServiceChargeAction();
			}

		});
		bottomButtonPanel.add(btAddServiceCharge);
		bottomButtonPanel.add(affiliationButton);
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
