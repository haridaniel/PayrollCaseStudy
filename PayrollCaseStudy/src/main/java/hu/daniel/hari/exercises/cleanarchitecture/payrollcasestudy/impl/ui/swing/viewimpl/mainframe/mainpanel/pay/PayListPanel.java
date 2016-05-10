package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.mainpanel.pay;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListView.PayListViewModel.PayListViewItem;

public class PayListPanel extends JPanel implements PayListView {
	private JTable table;
	
	public PayListPanel() {
		initUI();
	}

	private void initUI() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		table = new JTable();
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
	}

	@Override
	public void setModel(PayListViewModel viewModel) {
		table.setModel(new TableModelBuilder().toTableModel(viewModel));
	}

	private static class TableModelBuilder {
		public TableModel toTableModel(PayListViewModel viewModel) {
			return new DefaultTableModel(dataVector(viewModel.payListViewItems), columnNames());
		}
		private Vector<Vector<Object>> dataVector(List<PayListViewItem> payListViewItems) {
			Vector<Vector<Object>> data = new Vector<>();
			for (PayListViewItem payCheckListViewModel : payListViewItems) {
				data.add(new Vector<Object>(){{
					add(payCheckListViewModel.id);
					add(payCheckListViewModel.name);
					add(payCheckListViewModel.waging);
					add(payCheckListViewModel.grossAmount);
					add(payCheckListViewModel.deductionsAmount);
					add(payCheckListViewModel.netAmount);
					add(payCheckListViewModel.paymentMethod);
				}});
			}
			return data;
		}

		private Vector<String> columnNames() {
			Vector<String> columnNames = new Vector<String>() {{
				add("Id");
				add("Name");
				add("Waging");
				add("GrossAmount");
				add("DeductionsAmount");
				add("NetAmount");
				add("Method");
			}};
			return columnNames;
		}		
		
	}


}
