package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrameView extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrameView(ViewFactory viewFactory) {
		initUI();
		contentPane.add(viewFactory.employeeManagerView());
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Payroll - UI");
		setLocationByPlatform(true);
		setSize(450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
	}

	public void doShow() {
//		pack();
		setVisible(true);
	}

}
