package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.MainPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameView;

public class MainFrameWindow extends JFrame implements MainFrameView {
	private JPanel mainPanelHolder;
	private JPanel statusBarHolder;
	
	public MainFrameWindow(
			MainPanel mainPanel, 
			StatusBarPanel statusBarPanel
			) {
		initUI();
		mainPanelHolder.add(mainPanel);
		statusBarHolder.add(statusBarPanel);
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
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(contentPane);
		
		mainPanelHolder = new JPanel();
		centerPanel.add(mainPanelHolder);
		mainPanelHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		statusBarHolder = new JPanel();
		bottomPanel.add(statusBarHolder, BorderLayout.NORTH);
		statusBarHolder.setLayout(new BorderLayout(0, 0));
	}

}
