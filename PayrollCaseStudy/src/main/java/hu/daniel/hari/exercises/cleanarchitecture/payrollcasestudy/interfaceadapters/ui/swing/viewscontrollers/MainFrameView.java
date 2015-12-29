package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.ViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.components.statusbar.StatusBarView;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrameView extends JFrame {
	
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	
	private JButton addEmployeeButton;
	
	private MainFrameViewListener listener;

	/**
	 * Create the frame.
	 */
	public MainFrameView(ViewFactory viewFactory) {
		initUI();
		initViews(viewFactory);
	}

	private void initViews(ViewFactory viewFactory) {
		centerPanel.add(viewFactory.employeeManagerView());
		bottomPanel.add(viewFactory.statusBarView());
	}
	
	public void setListener(MainFrameViewListener listener) {
		this.listener = listener;
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Payroll - UI");
		setLocationByPlatform(true);
		setSize(450, 300);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		centerPanel.add(panel, BorderLayout.NORTH);
		
		addEmployeeButton = new JButton("Add Employee...");
		addEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onAddEmployeeAction();
			}
		});
		panel.add(addEmployeeButton);
		
		setContentPane(contentPane);
		
		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
	}

	public void doShow() {
		setVisible(true);
	}
	
	public static interface MainFrameViewListener {
		void onAddEmployeeAction();
	}
	
	
}
