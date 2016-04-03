package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel.payday.PayCheckListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.PayDayView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist.PayCheckListView;
import java.awt.FlowLayout;

public class PayDayPanel extends JPanel implements PayDayView {
	private JPanel tableHolder;
	private PayDayViewListener listener;
	private PayCheckListPanel payCheckListPanel;

	public PayDayPanel() {
		initUI();
		initPayCheckListPanel();
		initListeners();
	}
	
	@Override
	public void setListener(PayDayViewListener listener) {
		this.listener = listener;
	}

	private void initUI() {
	
		setLayout(new BorderLayout(0, 0));
	
		tableHolder = new JPanel();
		tableHolder.setLayout(new BorderLayout(0, 0));
		add(tableHolder, BorderLayout.CENTER);
	
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
	
		JButton sendPayButton = new JButton("Send Payments...");
		sendPayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onSendPayAction();
			}
		});

		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		buttonPanel.add(sendPayButton);
		
	}

	private void initPayCheckListPanel() {
		payCheckListPanel = new PayCheckListPanel();
		tableHolder.add(payCheckListPanel);
	}

	private void initListeners() {
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent event) {
			}
			@Override
			public void ancestorMoved(AncestorEvent event) {
			}
			@Override
			public void ancestorAdded(AncestorEvent event) {
				listener.onInitialized();
			}
		});
	}

	@Override
	public PayCheckListView getPayCheckListView() {
		return payCheckListPanel;
	}

}
