package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.SwingViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelView;
import java.awt.FlowLayout;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.DateField;
import javax.swing.JLabel;
import java.awt.Dimension;

public class MainPanel extends JPanel implements MainPanelView {
	private JPanel employeeManagerPanelHolder;
	private JPanel payDayPanelHolder;
	private DateField dateField;
	
	private MainPanelViewListener listener;

	public MainPanel(SwingViewFactory swingViewFactory) {
		initUI();
		initSubViews(swingViewFactory);
		initState();
	}

	private void initState() {
		dateField.setValue(LocalDate.of(2016, 02, 12));
	}

	private void initSubViews(SwingViewFactory swingViewFactory) {
		employeeManagerPanelHolder.add(swingViewFactory.employeeManagerPanel());
		payDayPanelHolder.add(swingViewFactory.payDayPanel());
	}
	
	@Override
	public void setListener(MainPanelViewListener listener) {
		this.listener = listener;
	}

	private void initUI() {
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		topPanel.add(buttonPanel, BorderLayout.NORTH);
		
		JButton addEmployeeButton = new JButton("Add Employee...");
		addEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onAddEmployeeAction();
			}
		});
		setLayout(new BorderLayout(0, 0));
		buttonPanel.add(addEmployeeButton);
		
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		employeeManagerPanelHolder = new JPanel();
		centerPanel.add(employeeManagerPanelHolder);
		employeeManagerPanelHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		centerPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Date");
		panel_1.add(lblNewLabel);
		
		dateField = new DateField();
		dateField.setPreferredSize(new Dimension(100, 20));
		dateField.setMinimumSize(new Dimension(50, 20));
		panel_1.add(dateField);
		
		payDayPanelHolder = new JPanel();
		panel.add(payDayPanelHolder, BorderLayout.SOUTH);
		payDayPanelHolder.setLayout(new BorderLayout(0, 0));
		
		add(contentPane);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
	}

}
