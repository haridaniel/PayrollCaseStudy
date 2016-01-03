package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.SwingViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table.EmployeesTablePanel;

public class EmployeeManagerPanel extends JPanel implements EmployeeManagerView {
	private JPanel tablePanel;
	private EmployeesTablePanel employeesTablePanel;
	private EmployeeManagerViewListener listener;
	private JButton deleteButton;

	public EmployeeManagerPanel(SwingViewFactory swingViewFactory) {
		initUI();
		initEmloyeesTableView(swingViewFactory);
	}

	private void initEmloyeesTableView(SwingViewFactory swingViewFactory) {
		this.employeesTablePanel = swingViewFactory.employeesTablePanel();
		tablePanel.add(employeesTablePanel);
	}

	@Override
	public void setListener(EmployeeManagerViewListener listener) {
		this.listener = listener;
	}
	
	private void initUI() {

		setLayout(new BorderLayout(0, 0));

		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		add(tablePanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.EAST);
		
				deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onDeleteAction();
					}
				});
		
				JButton btnNewButton_1 = new JButton("New button");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
				gl_buttonPanel.setHorizontalGroup(
					gl_buttonPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_buttonPanel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)
							.addContainerGap())
						.addGroup(gl_buttonPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_buttonPanel.setVerticalGroup(
					gl_buttonPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_buttonPanel.createSequentialGroup()
							.addComponent(deleteButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)
							.addGap(248))
				);
				buttonPanel.setLayout(gl_buttonPanel);

	}

	private void onDeleteAction() {
		listener.onDeleteAction();
	}
	
	@Override
	public void enableButtons() {
		setButtonsEnabled(true);
	}

	@Override
	public void disableButtons() {
		setButtonsEnabled(false);
	}

	private void setButtonsEnabled(boolean enabled) {
		deleteButton.setEnabled(enabled);
	}

}
