package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.usecase.AddEmployeeUseCaseValidationErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.viewmodel.FieldValidatorErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DefaultClosableViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.EmployeeViewModelVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator.HourlyRequestCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator.SalariedRequestCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.AddHourlyEmployeeFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.AddSalariedEmployeeFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeUseCaseValidationException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeUseCaseValidationException.AddEmployeeValidationError;

public class AddEmployeeController extends DefaultClosableViewController<AddEmployeeView> implements AddEmployeeViewListener {

	private AddEmployeeUseCaseFactory useCaseFactory;
	private EventBus eventBus;

	@Inject
	public AddEmployeeController(AddEmployeeUseCaseFactory addEmployeeUseCaseFactory, EventBus eventBus) {
		super(eventBus);
		this.useCaseFactory = addEmployeeUseCaseFactory;
		this.eventBus = eventBus;
	}

	@Override
	protected boolean isAllowedToCloseNow() {
		return true;
	}

	@Override
	public void onAddEmployee() {
		getView().getModel().accept(new OnAddEmployeeHandlerExecutor());
	}

	@Override
	public void onCancel() {
		close();
	}

	private class OnAddEmployeeHandlerExecutor implements EmployeeViewModelVisitor {
		@Override
		public void visit(SalariedEmployeeViewModel salariedEmployeeViewModel) {
			new OnAddSalariedEmployeeHandler().onAddEmployee(salariedEmployeeViewModel);
		}

		@Override
		public void visit(HourlyEmployeeViewModel hourlyEmployeeViewModel) {
			new OnAddHourlyEmployeeHandler().onAddEmployee(hourlyEmployeeViewModel);
		}
	}
	
	private abstract class OnAddEmployeeHandler<T extends EmployeeViewModel> {

		public void onAddEmployee(T model) {
			try {
				validateFields(model);
				executeUseCase(model);
				eventBus.post(new AddedEmployeeEvent(model.employeeId.get(), model.name));
				close();
			} catch (FieldValidatorException e) {
				new FieldValidatorErrorHandler(e.fieldValidatorErrors);
			} catch (AddEmployeeUseCaseValidationException e) {
				new UseCaseValidationErrorHandler(e.addEmployeeValidationErrors);
			}
		}

		protected abstract void validateFields(T model);
		protected abstract void executeUseCase(T model);
		
	}
	private class OnAddSalariedEmployeeHandler extends OnAddEmployeeHandler<SalariedEmployeeViewModel> {
		@Override
		protected void validateFields(SalariedEmployeeViewModel model) {
			new AddSalariedEmployeeFieldsValidator(model);
		}
		@Override
		protected void executeUseCase(SalariedEmployeeViewModel model) {
			useCaseFactory.addSalariedEmployeeUseCase().execute(new SalariedRequestCreator().toRequest(model));
		}
	}
	
	private class OnAddHourlyEmployeeHandler extends OnAddEmployeeHandler<HourlyEmployeeViewModel> {
		@Override
		protected void validateFields(HourlyEmployeeViewModel model) {
			new AddHourlyEmployeeFieldsValidator(model);
		}

		@Override
		protected void executeUseCase(HourlyEmployeeViewModel model) {
			useCaseFactory.addHourlyEmployeeUseCase().execute(new HourlyRequestCreator().toRequest(model));
		}
	}
	
	private class FieldValidatorErrorHandler {
		private FieldValidatorErrorFormatter errorFormatter = new FieldValidatorErrorFormatter();
		
		public FieldValidatorErrorHandler(List<FieldValidatorError> fieldValidatorErrors) {
			getView().setModel(new ValidationErrorMessagesModel(format(fieldValidatorErrors)));
		}

		private List<String> format(List<FieldValidatorError> fieldValidatorErrors) {
			return fieldValidatorErrors.stream()
					.map(e -> format(e))
					.collect(Collectors.toList());
		}

		private String format(FieldValidatorError e) {
			return errorFormatter.format(e);
		}
	}
	
	private class UseCaseValidationErrorHandler {
		private AddEmployeeUseCaseValidationErrorFormatter errorFormatter = new AddEmployeeUseCaseValidationErrorFormatter();
		
		public UseCaseValidationErrorHandler(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
			getView().setModel(new ValidationErrorMessagesModel(format(addEmployeeValidationErrors)));
		}

		private List<String> format(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
			return addEmployeeValidationErrors.stream()
				.map(e -> format(e))
				.collect(Collectors.toList());
		}

		private String format(AddEmployeeValidationError e) {
			return e.accept(errorFormatter);
		}
		
	}
}
