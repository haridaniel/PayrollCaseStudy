package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel.payday;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist.PayCheckListView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist.PayCheckListView.PayCheckListViewModel.PayCheckViewModel;

public class PayCheckListPanel extends JPanel implements PayCheckListView {
	private JTable table;
	
	public PayCheckListPanel() {
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
	public void setModel(PayCheckListViewModel viewModel) {
		table.setModel(new TableModelBuilder().toTableModel(viewModel));
	}

	private static class TableModelBuilder {
		public TableModel toTableModel(PayCheckListViewModel viewModel) {
			return new DefaultTableModel(dataVector(viewModel.payCheckViewModels), columnNames());
		}
		private Vector<Vector<Object>> dataVector(List<PayCheckViewModel> payCheckViewModels) {
			Vector<Vector<Object>> data = new Vector<>();
			for (PayCheckViewModel payCheckListViewModel : payCheckViewModels) {
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
