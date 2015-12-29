package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table.EmployeesTableViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse.EmployeeItem;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import javax.swing.JScrollPane;

public class EmployeesTableView extends JPanel {
	private JTable table;
	private EmployeesOverviewPanelListener listener;
	private EventBus eventBus;
	private EmployeesTableViewModel viewModel;
	
	/**
	 * Create the panel.
	 * @param eventBus 
	 */
	public EmployeesTableView(EventBus eventBus) {
		this.eventBus = eventBus;
		initUI();
		initListener();
		initEvents();
		postEmployeeSelectionChangedEvent();
	}

	private void initEvents() {
		table.getSelectionModel().addListSelectionListener(e -> { 
			postEmployeeSelectionChangedEvent();
		});
	}

	private void postEmployeeSelectionChangedEvent() {
		eventBus.post(new EmployeeSelectionChangedEvent(getSelectedEmployeeId()));
	}
	
	private Optional<Integer> getSelectedEmployeeId() {
		ListSelectionModel selectionModel = table.getSelectionModel();
		if(selectionModel.isSelectionEmpty())
			return Optional.empty();
		
		int index = selectionModel.getMinSelectionIndex();
		EmployeeViewItem employeeViewItem = viewModel.employeeViewItems.get(index);
		return Optional.of(employeeViewItem.id);
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

	public void setModel(EmployeesTableViewModel viewModel) {
		this.viewModel = viewModel;
		table.setModel(new TableModelBuilder().toTableModel(viewModel));
	}
	
	private static class TableModelBuilder {
		public TableModel toTableModel(EmployeesTableViewModel viewModel) {
			return new DefaultTableModel(dataVector(viewModel.employeeViewItems), columnNames());
		}
		private Vector<Vector<Object>> dataVector(List<EmployeeViewItem> employeeViewItems) {
			Vector<Vector<Object>> data = new Vector<>();
			for (EmployeeViewItem employeeItem : employeeViewItems) {
				data.add(new Vector<Object>(){{
					add(employeeItem.id);
					add(employeeItem.name);
					add(employeeItem.address);
					add(employeeItem.paymentClassificationType);
				}});
			}
			return data;
		}

		private Vector<String> columnNames() {
			Vector<String> columnNames = new Vector<String>() {{
				add("Id");
				add("Name");
				add("Address");
				add("Type");
			}};
			return columnNames;
		}		
		
	}

	private void initUI() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		table = new JTable();
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	
	public static interface EmployeesOverviewPanelListener {
		void onViewAdded();
	}

}
