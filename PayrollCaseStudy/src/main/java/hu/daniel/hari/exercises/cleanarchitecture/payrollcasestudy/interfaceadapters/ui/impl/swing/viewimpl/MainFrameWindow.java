package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing.viewimpl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing.SwingViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe.MainFrameView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe.MainFrameView.MainFrameViewListener;

public class MainFrameWindow extends JFrame implements MainFrameView {
	
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	
	private JButton addEmployeeButton;
	
	private MainFrameViewListener listener;

	public MainFrameWindow(SwingViewFactory swingViewFactory) {
		initUI();
		initSubViews(swingViewFactory);
	}

	private void initSubViews(SwingViewFactory swingViewFactory) {
		centerPanel.add(swingViewFactory.employeeManagerPanel());
		bottomPanel.add(swingViewFactory.statusBarPanel());
	}
	
	@Override
	public void setListener(MainFrameViewListener listener) {
		this.listener = listener;
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Payroll - UI");
		setLocationByPlatform(true);
		setSize(650, 500);
		
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

}
