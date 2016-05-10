package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.MainPanelView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.UIImplConstants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.field.DateField;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.PayPanel;

public class MainPanel extends JPanel implements MainPanelView {
	private JPanel employeeManagerPanelHolder;
	private JPanel payDayPanelHolder;
	private DateField currentDateField;
	private MainPanelViewListener listener;
	private String dateFormat = UIImplConstants.DATE_FORMAT;
	
	public MainPanel(EmployeeManagerPanel employeeManagerPanel, PayPanel payPanel) {
		initUI();
		employeeManagerPanelHolder.add(employeeManagerPanel);
		payDayPanelHolder.add(payPanel);
	}

	private void initUI() {
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		topPanel.add(panel_1, BorderLayout.NORTH);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		currentDateField = new DateField(dateFormat);
		currentDateField.addPropertyChangeListener("value", new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent evt) {
	        	onCurrentDateChanged();
	        }
	    });
		currentDateField.setPreferredSize(new Dimension(100, 20));
		currentDateField.setMinimumSize(new Dimension(50, 20));
		panel_1.add(currentDateField);
		
		JLabel lblNewLabel = new JLabel("- Current date");
		panel_1.add(lblNewLabel);
		
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		topPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		centerPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEmployees = new JLabel("Employees");
		panel_2.add(lblEmployees, BorderLayout.NORTH);
		
		employeeManagerPanelHolder = new JPanel();
		panel_2.add(employeeManagerPanelHolder);
		employeeManagerPanelHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		centerPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Today's payments");
		panel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		payDayPanelHolder = new JPanel();
		panel.add(payDayPanelHolder, BorderLayout.CENTER);
		payDayPanelHolder.setLayout(new BorderLayout(0, 0));
		
		add(contentPane);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
	}

	private void onCurrentDateChanged() {
		listener.onChangedCurrentDate();
	}

	@Override
	public void setViewListener(MainPanelViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setModel(MainPanelViewModel viewModel) {
		currentDateField.setDate(viewModel.currentDate);
	}

	@Override
	public MainPanelViewModel getModel() {
		return new MainPanelViewModel(currentDateField.getDate());
	}

}
