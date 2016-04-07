package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DefaultClosableViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeValidationErrorsModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.NameAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.AddEmployeeValidationError;

public class AddEmployeeController extends DefaultClosableViewController<AddEmployeeView> implements AddEmployeeViewListener {

	private AddEmployeeUseCaseFactory useCaseFactory;
	private EventBus eventBus;
	private RequestCreator requestCreator = new RequestCreator();

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
		AddEmployeeViewModel model = getView().getModel();
		try {
			useCaseFactory.addSalariedEmployeeUseCase().execute(requestCreator.toRequest(model));
			eventBus.post(new AddedEmployeeEvent(model.employeeId, model.name));
			close();
		} catch (AddEmployeeValidationException e) {
			new ErrorHandler(e.addEmployeeValidationErrors);
		}
	}

	@Override
	public void onCancel() {
		close();
	}
	
	private static class RequestCreator {
		public AddSalariedEmployeeRequest toRequest(AddEmployeeViewModel model) {
			int monthlySalary = 0; //TODO
			return new AddSalariedEmployeeRequest(
					model.employeeId, 
					model.name, 
					model.address, 
					monthlySalary
					);
		}
		
	}

	private class ErrorHandler {
		public ErrorHandler(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
			getView().setModel(new AddEmployeeValidationErrorsModel(createErrorMessages(addEmployeeValidationErrors)));
		}

		private List<String> createErrorMessages(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
			List<String> errorMessages = new ArrayList<>();
			for (AddEmployeeValidationError addEmployeeValidationError : addEmployeeValidationErrors) {
				errorMessages.add(createErrorMessage(addEmployeeValidationError));
			}
			return errorMessages;
		}

		private String createErrorMessage(AddEmployeeValidationError addEmployeeValidationError) {
			if(addEmployeeValidationError instanceof IdAlreadyExistsValidationError) {
				return String.format("%s already owns this id!", ((IdAlreadyExistsValidationError) addEmployeeValidationError).nameOfExistingUser);
			} else if(addEmployeeValidationError instanceof NameAlreadyExistsValidationError) {
				return String.format("Name already exists with id: %s", ((NameAlreadyExistsValidationError) addEmployeeValidationError).idOfExistingUser);
			}
			throw new RuntimeException("not implemented");
		}
		
	}
}
