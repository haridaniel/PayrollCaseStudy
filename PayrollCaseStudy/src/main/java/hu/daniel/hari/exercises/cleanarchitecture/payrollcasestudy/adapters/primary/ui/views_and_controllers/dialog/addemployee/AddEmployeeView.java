package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DialogView;

public interface AddEmployeeView extends 
	DialogView<AddEmployeeView.AddEmployeeViewListener>,
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
		
		public interface PaymentMethod {
			
			<T> T accept(PaymentMethodVisitor<T> visitor);
			public interface PaymentMethodVisitor<T> {
				T visit(PaymasterPaymentMethod paymentMethod);
				T visit(DirectPaymentMethod paymentMethod);
			}
		}
		public static class PaymasterPaymentMethod implements PaymentMethod {
			@Override
			public <T> T accept(PaymentMethodVisitor<T> visitor) {
				return visitor.visit(this);
			}
		}
		public static class DirectPaymentMethod implements PaymentMethod {
			public String accountNumber;
			public DirectPaymentMethod(String accountNumber) {
				this.accountNumber = accountNumber;
			}
			@Override
			public <T> T accept(PaymentMethodVisitor<T> visitor) {
				return visitor.visit(this);
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