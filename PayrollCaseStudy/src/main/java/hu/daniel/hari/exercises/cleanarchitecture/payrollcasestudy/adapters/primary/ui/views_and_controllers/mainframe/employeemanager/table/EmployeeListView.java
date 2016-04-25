package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.util.List;
import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem;

public interface EmployeeListView extends
	ControlView<EmployeeListView.EmployeeListViewListener>,
	ModelConsumer<EmployeeListView.EmployeeListViewModel>
{

	public static interface EmployeeListViewListener {
		void onSelectionChanged(Optional<EmployeeViewItem> employee);
	}
	
	public static class EmployeeListViewModel {
		public List<EmployeeViewItem> employeeViewItems; 

		public EmployeeListViewModel(List<EmployeeViewItem> employeeViewItems) {
			this.employeeViewItems = employeeViewItems;
		}

		public static class EmployeeViewItem {
			public int id;
			public String name;
			public String address;
			public String waging;
			public String nextPayDay;
			public PaymentType paymentType;
			
			public enum PaymentType {
				SALARIED {@Override public void accept(PaymentTypeVisitor visitor) {visitor.visitSalaried();}},		
				HOURLY {@Override public void accept(PaymentTypeVisitor visitor) {visitor.visitHourly();}},
				COMMISSIONED {@Override public void accept(PaymentTypeVisitor visitor) {visitor.visitCommissioned();}};
				
				public abstract void accept(PaymentTypeVisitor visitor);

				public interface PaymentTypeVisitor {
					void visitCommissioned();
					void visitHourly();
					void visitSalaried();
				}

				
			}
			
		}
		
	}

	
}