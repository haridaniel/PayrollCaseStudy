package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.SwingViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameView;

public class MainFrameWindow extends JFrame implements MainFrameView {
	private JPanel employeeManagerPanelHolder;
	private JPanel payDayPanelHolder;
	private JPanel statusBarHolder;
	
	private MainFrameViewListener listener;

	public MainFrameWindow(SwingViewFactory swingViewFactory) {
		initUI();
		initSubViews(swingViewFactory);
	}

	private void initSubViews(SwingViewFactory swingViewFactory) {
		employeeManagerPanelHolder.add(swingViewFactory.employeeManagerPanel());
		payDayPanelHolder.add(swingViewFactory.payDayPanel());
		statusBarHolder.add(swingViewFactory.statusBarPanel());
	}
	
	@Override
	public void setListener(MainFrameViewListener listener) {
		this.listener = listener;
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Payroll - UI");
		setSize(850, 500);
		setLocationByPlatform(true);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		topPanel.add(buttonPanel, BorderLayout.NORTH);
		
		JButton addEmployeeButton = new JButton("Add Employee...");
		addEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onAddEmployeeAction();
			}
		});
		buttonPanel.add(addEmployeeButton);
		
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		employeeManagerPanelHolder = new JPanel();
		centerPanel.add(employeeManagerPanelHolder);
		employeeManagerPanelHolder.setLayout(new BorderLayout(0, 0));
		
		payDayPanelHolder = new JPanel();
		centerPanel.add(payDayPanelHolder);
		payDayPanelHolder.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		statusBarHolder = new JPanel();
		bottomPanel.add(statusBarHolder, BorderLayout.NORTH);
		statusBarHolder.setLayout(new BorderLayout(0, 0));
	}

}
