package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainpanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.SwingViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;

public class EmployeeManagerPanel extends JPanel implements EmployeeManagerView {
	private JPanel tablePanel;
	private EmployeeManagerViewListener listener;
	private JButton deleteButton;

	public EmployeeManagerPanel(SwingViewFactory swingViewFactory) {
		initUI();
		initTable(swingViewFactory);
	}

	private void initTable(SwingViewFactory swingViewFactory) {
		tablePanel.add(swingViewFactory.employeesTablePanel());
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
	
		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(
				gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_buttonPanel.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
						)
				);
		buttonPanel.setLayout(gl_buttonPanel);
	
	}

}
