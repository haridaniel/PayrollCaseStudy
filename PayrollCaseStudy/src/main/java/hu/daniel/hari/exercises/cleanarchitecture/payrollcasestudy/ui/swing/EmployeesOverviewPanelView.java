package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;
import javax.swing.JScrollPane;

public class EmployeesOverviewPanelView extends JPanel {
	private JTable table;

	private EmployeesOverviewPanelListener listener;
	
	/**
	 * Create the panel.
	 */
	public EmployeesOverviewPanelView() {
		initUI();
		initListener();
	}
	

	private void initListener() {
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent event) {
			}
			@Override
			public void ancestorMoved(AncestorEvent event) {
			}
			@Override
			public void ancestorAdded(AncestorEvent event) {
				listener.onViewAdded();
			}
		});
	}
	
	public void setListener(EmployeesOverviewPanelListener listener) {
		this.listener = listener;
	}

	public void setModel(EmployeesOverviewPanelViewModel viewModel) {
		table.setModel(viewModel.tableModel);
	}
	
	private void initUI() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public static interface EmployeesOverviewPanelListener {
		void onViewAdded();
	}

}
