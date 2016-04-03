package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.SwingViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;

public class EmployeeManagerPanel extends JPanel implements EmployeeManagerView {
	private JPanel tableHolder;
	private EmployeeManagerViewListener listener;
	private JButton deleteButton;

	public EmployeeManagerPanel(EmployeeListPanel employeeListPanel) {
		initUI();
		tableHolder.add(employeeListPanel);
	}

	@Override
	public void setListener(EmployeeManagerViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setButtonsEnabled(boolean enabled) {
		deleteButton.setEnabled(enabled);
	}

	private void onDeleteAction() {
		listener.onDeleteAction();
	}
	
	private void onAddAction() {
		listener.onAddAction();
	}

	private void initUI() {
	
		setLayout(new BorderLayout(0, 0));
	
		tableHolder = new JPanel();
		tableHolder.setLayout(new BorderLayout(0, 0));
		add(tableHolder, BorderLayout.CENTER);
	
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.EAST);
	
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDeleteAction();
			}
		});
		
		JButton addButton = new JButton("Add...");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddAction();
			}
		});
	
		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(addButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
						.addComponent(deleteButton, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_buttonPanel.setVerticalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deleteButton)
					.addGap(237))
		);
		buttonPanel.setLayout(gl_buttonPanel);
	
	}
}
