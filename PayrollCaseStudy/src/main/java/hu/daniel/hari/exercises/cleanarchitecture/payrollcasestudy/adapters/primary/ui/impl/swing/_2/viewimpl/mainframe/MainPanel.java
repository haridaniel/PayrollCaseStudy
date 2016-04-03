package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel.PayDayPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.component.DateField;

public class MainPanel extends JPanel {
	private JPanel employeeManagerPanelHolder;
	private JPanel payDayPanelHolder;
	private DateField dateField;
	
	public MainPanel(EmployeeManagerPanel employeeManagerPanel, PayDayPanel payDayPanel) {
		initUI();
		employeeManagerPanelHolder.add(employeeManagerPanel);
		payDayPanelHolder.add(payDayPanel);
		initState();
	}

	private void initState() {
		dateField.setValue(LocalDate.of(2016, 04, 8));
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
		
		setLayout(new BorderLayout(0, 0));
		
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
		panel.add(payDayPanelHolder, BorderLayout.CENTER);
		payDayPanelHolder.setLayout(new BorderLayout(0, 0));
		
		add(contentPane);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
	}

}
