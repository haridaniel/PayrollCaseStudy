package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._1.viewimpl.mainpanel;

import java.awt.GridLayout;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem;

public class EmployeesTablePanel extends JPanel implements EmployeeListView {
	private JTable table;
	private EmployeeListViewListener listener;
	private EmployeeListViewModel viewModel;
	
	public EmployeesTablePanel() {
		initUI();
		initListener();
		initEvents();
	}

	private void initUI() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		table = new JTable();
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
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
				listener.onInitialized();
			}
		});
	}

	private void initEvents() {
		table.getSelectionModel().addListSelectionListener(e -> { 
			fireEmployeeSelectionChangedEvent();
		});
	}

	private void fireEmployeeSelectionChangedEvent() {
		listener.onSelectionChanged(getOptionalSelectedEmployeeId());
	}

	private Optional<Integer> getOptionalSelectedEmployeeId() {
		ListSelectionModel selectionModel = table.getSelectionModel();
		if(selectionModel.isSelectionEmpty())
			return Optional.empty();
		
		int index = selectionModel.getMinSelectionIndex();
		EmployeeViewItem employeeViewItem = viewModel.employeeViewItems.get(index);
		return Optional.of(employeeViewItem.id);
	}


	@Override
	public void setListener(EmployeeListViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setModel(EmployeeListViewModel viewModel) {
		this.viewModel = viewModel;
		table.setModel(new TableModelBuilder().toTableModel(viewModel));
		fireEmployeeSelectionChangedEvent();
	}
	
	private static class TableModelBuilder {
		public TableModel toTableModel(EmployeeListViewModel viewModel) {
			return new DefaultTableModel(dataVector(viewModel.employeeViewItems), columnNames());
		}
		private Vector<Vector<Object>> dataVector(List<EmployeeViewItem> employeeViewItems) {
			Vector<Vector<Object>> data = new Vector<>();
			for (EmployeeViewItem employeeItem : employeeViewItems) {
				data.add(new Vector<Object>(){{
					add(employeeItem.id);
					add(employeeItem.name);
					add(employeeItem.address);
					add(employeeItem.waging);
					add(employeeItem.nextPayDay);
				}});
			}
			return data;
		}

		private Vector<String> columnNames() {
			Vector<String> columnNames = new Vector<String>() {{
				add("Id");
				add("Name");
				add("Address");
				add("Waging");
				add("Following pay-day");
			}};
			return columnNames;
		}		
		
	}

}
