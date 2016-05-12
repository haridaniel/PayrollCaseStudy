package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.PayView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.pay.PayListPanel;

public class PayPanel extends JPanel implements PayView {
	private JPanel tableHolder;
	private PayViewListener listener;
	private JButton btFulfillPayment;

	public PayPanel(
			PayListPanel payListPanel			
			) {
		initUI();
		tableHolder.add(payListPanel);
	}
	
	@Override
	public void setViewListener(PayViewListener listener) {
		this.listener = listener;
	}

	private void initUI() {
	
		setLayout(new BorderLayout(0, 0));
	
		tableHolder = new JPanel();
		tableHolder.setLayout(new BorderLayout(0, 0));
		add(tableHolder, BorderLayout.CENTER);
	
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
	
		btFulfillPayment = new JButton("Fulfill Payments...");
		btFulfillPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onFulfillPayAction();
			}
		});

		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		buttonPanel.add(btFulfillPayment);
		
	}

	@Override
	public void setModel(PayViewModel viewModel) {
		btFulfillPayment.setEnabled(viewModel.isFulfillButtonEnabled);
	}

}
