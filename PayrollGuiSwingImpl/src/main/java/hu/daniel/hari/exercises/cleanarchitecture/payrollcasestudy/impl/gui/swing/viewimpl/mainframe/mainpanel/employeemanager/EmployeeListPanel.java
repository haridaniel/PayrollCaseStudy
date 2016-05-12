package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.employeemanager;

import java.awt.GridLayout;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Vector;
import java.util.stream.IntStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListView;

public class EmployeeListPanel extends JPanel implements EmployeeListView {
	private JTable table;
	private EmployeeListViewListener listener;
	private EmployeeListViewModel viewModel;
	
	public EmployeeListPanel() {
		initUI();
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

	private void initEvents() {
		table.getSelectionModel().addListSelectionListener(e -> { 
			fireEmployeeSelectionChangedEvent();
		});
	}

	private void fireEmployeeSelectionChangedEvent() {
		listener.onSelectionChanged(getOptionalSelectedEmployeeViewItem());
	}

	private Optional<EmployeeViewItem> getOptionalSelectedEmployeeViewItem() {
		ListSelectionModel selectionModel = table.getSelectionModel();
		if(selectionModel.isSelectionEmpty())
			return Optional.empty();
		return Optional.of(viewModel.employeeViewItems.get(selectionModel.getMinSelectionIndex()));
	}


	@Override
	public void setViewListener(EmployeeListViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setModel(EmployeeListViewModel viewModel) {
		Optional<EmployeeViewItem> lastSelectedEmployee = getOptionalSelectedEmployeeViewItem();
		this.viewModel = viewModel;
		table.setModel(new TableModelBuilder().toTableModel(viewModel));
		selectEmployeeIfPossible(lastSelectedEmployee);
		fireEmployeeSelectionChangedEvent();
	}

	private void selectEmployeeIfPossible(Optional<EmployeeViewItem> employee) {
		employee.ifPresent((e) -> {
			selectEmployeeIfExists(e.id);
		});
	}
	
	private void selectEmployeeIfExists(int employeeId) {
		getIndexOf(employeeId).ifPresent((i) -> {
			table.getSelectionModel().setSelectionInterval(i, i);
		});
	}

	private OptionalInt getIndexOf(int employeeId) {
		return IntStream.range(0, viewModel.employeeViewItems.size())
			.filter((i) -> viewModel.employeeViewItems.get(i).id == employeeId)
			.findFirst();
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
