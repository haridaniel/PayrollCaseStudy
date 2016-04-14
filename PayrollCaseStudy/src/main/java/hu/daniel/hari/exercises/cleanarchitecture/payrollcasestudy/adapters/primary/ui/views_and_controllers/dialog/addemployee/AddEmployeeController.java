package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.AddEmployeeValidationErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DefaultClosableViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeValidationErrorsModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.EmployeeViewModelVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.AddEmployeeValidationError;

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
		try {
			getView().getModel().accept(new UseCaseExecutor());
			eventBus.post(new AddedEmployeeEvent(getView().getModel().employeeId, getView().getModel().name));
			close();
		} catch (AddEmployeeValidationException e) {
			new ErrorHandler(e.addEmployeeValidationErrors);
		}
	}

	@Override
	public void onCancel() {
		close();
	}
	
	private class UseCaseExecutor implements EmployeeViewModelVisitor {
		@Override
		public void visit(SalariedEmployeeViewModel salariedEmployeeViewModel) {
			useCaseFactory.addSalariedEmployeeUseCase().execute(new SalariedRequestCreator().toRequest(salariedEmployeeViewModel));
		}

		@Override
		public void visit(HourlyEmployeeViewModel hourlyEmployeeViewModel) {
			useCaseFactory.addHourlyEmployeeUseCase().execute(new HourlyRequestCreator().toRequest(hourlyEmployeeViewModel));
		}
		
	}
	
	private static abstract class RequestCreator<I extends EmployeeViewModel, O extends AddEmployeeRequest> {
		public O toRequest(I model) {
			return fill(model, toSpecificRequest(model));
		}

		private O fill(I model, O request) {
			request.employeeId = model.employeeId;
			request.name = model.name;
			request.address = model.address;
			return request;
		}
		
		protected abstract O toSpecificRequest(I model);
	}
	
	private static class SalariedRequestCreator extends RequestCreator<SalariedEmployeeViewModel, AddSalariedEmployeeRequest> {
		@Override
		protected AddSalariedEmployeeRequest toSpecificRequest(SalariedEmployeeViewModel model) {
			AddSalariedEmployeeRequest addSalariedEmployeeRequest = new AddSalariedEmployeeRequest();
			addSalariedEmployeeRequest.monthlySalary = model.monthlySalary;
			return addSalariedEmployeeRequest;
		}
	}
	
	private static class HourlyRequestCreator extends RequestCreator<HourlyEmployeeViewModel, AddHourlyEmployeeRequest> {
		@Override
		protected AddHourlyEmployeeRequest toSpecificRequest(HourlyEmployeeViewModel model) {
			AddHourlyEmployeeRequest addHourlyEmployeeRequest = new AddHourlyEmployeeRequest();
			addHourlyEmployeeRequest.hourlyWage = model.hourlyWage;
			return addHourlyEmployeeRequest;
		}
	}

	private class ErrorHandler {
		private AddEmployeeValidationErrorFormatter addEmployeeValidationErrorFormatter = new AddEmployeeValidationErrorFormatter();
		
		public ErrorHandler(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
			getView().setModel(new AddEmployeeValidationErrorsModel(format(addEmployeeValidationErrors)));
		}

		private List<String> format(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
			return addEmployeeValidationErrors.stream()
				.map(e -> e.accept(addEmployeeValidationErrorFormatter))
				.collect(Collectors.toList());
		}
		
	}
}
