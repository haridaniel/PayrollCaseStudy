package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelProducer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.ClosableView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;

public interface AddEmployeeView extends ClosableView<AddEmployeeView.AddEmployeeViewListener>,
	ModelProducer<AddEmployeeView.AddEmployeeViewModel>,
	ModelConsumer<AddEmployeeView.AddEmployeeValidationErrorsModel>
{
	
	public interface AddEmployeeViewListener extends CloseableViewListener {
		void onAddEmployee();
		void onCancel();
	}

	public class AddEmployeeViewModel {
		public Integer employeeId;
		public String name;
		public String address;
	}
	
	public class AddEmployeeValidationErrorsModel {
		public List<String> useCaseValidationErrorMessages;

		public AddEmployeeValidationErrorsModel(List<String> useCaseValidationErrorMessages) {
			this.useCaseValidationErrorMessages = useCaseValidationErrorMessages;
		}
		
	}

}