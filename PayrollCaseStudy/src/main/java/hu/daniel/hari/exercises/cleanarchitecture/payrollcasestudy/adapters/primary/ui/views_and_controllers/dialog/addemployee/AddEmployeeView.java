package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.ClosableView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;

public interface AddEmployeeView extends ClosableView<AddEmployeeView.AddEmployeeViewListener>,
	ModelProducer<AddEmployeeView.EmployeeViewModel>,
	ModelConsumer<ValidationErrorMessagesModel>
{
	
	public interface AddEmployeeViewListener extends CloseableViewListener {
		void onAddEmployee();
		void onCancel();
	}

	public abstract class EmployeeViewModel {
		public Optional<Integer> employeeId;
		public String name;
		public String address;
		public PaymentMethod paymentMethod;
		
		public static class PaymentMethod {
		}
		public static class PaymasterPaymentMethod extends PaymentMethod {
		}
		public static class DirectPaymentMethod extends PaymentMethod {
			public String accountNumber;
			public DirectPaymentMethod(String accountNumber) {
				this.accountNumber = accountNumber;
			}
		}
		
		public abstract void accept(EmployeeViewModelVisitor visitor);
		public interface EmployeeViewModelVisitor {
			void visit(SalariedEmployeeViewModel salariedEmployeeViewModel);
			void visit(HourlyEmployeeViewModel hourlyEmployeeViewModel);
			void visit(CommissionedEmployeeViewModel commissionedEmployeeViewModel);
		}
	}
	
	public class SalariedEmployeeViewModel extends EmployeeViewModel {
		public Integer monthlySalary;
		@Override
		public void accept(EmployeeViewModelVisitor visitor) {
			visitor.visit(this);
		}
	}
	
	public class HourlyEmployeeViewModel extends EmployeeViewModel {
		public Integer hourlyWage;
		@Override
		public void accept(EmployeeViewModelVisitor visitor) {
			visitor.visit(this);
		}
	}

	public class CommissionedEmployeeViewModel extends EmployeeViewModel {
		public Integer biWeeklyBaseSalary;
		public Integer commissionRatePercentage;
		@Override
		public void accept(EmployeeViewModelVisitor visitor) {
			visitor.visit(this);
		}
	}
	
}