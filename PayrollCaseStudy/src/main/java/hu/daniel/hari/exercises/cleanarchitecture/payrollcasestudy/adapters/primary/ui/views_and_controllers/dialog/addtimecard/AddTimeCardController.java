package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.usecase.AddEmployeeUseCaseValidationErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DefaultClosableViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.EmployeeViewModelVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewInputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewOutputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeUseCaseValidationException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeUseCaseValidationException.AddEmployeeValidationError;

public class AddTimeCardController extends DefaultClosableViewController<AddTimeCardView> implements AddTimeCardViewListener {

	private EventBus eventBus;
	private int employeeId;
	private GetEmployeeUseCaseFactory getEmployeeUseCaseFactory;
	private AddTimeCardUseCaseFactory addTimeCardUseCaseFactory;

	@Inject
	public AddTimeCardController(
			EventBus eventBus,
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory,
			AddTimeCardUseCaseFactory addTimeCardUseCaseFactory,
			@Assisted int employeeId
			) {
		super(eventBus);
		this.eventBus = eventBus;
		this.getEmployeeUseCaseFactory = getEmployeeUseCaseFactory;
		this.addTimeCardUseCaseFactory = addTimeCardUseCaseFactory;
		this.employeeId = employeeId;
	}

	@Override
	protected boolean isAllowedToCloseNow() {
		return true;
	}
	
	@Override
	public void setView(AddTimeCardView view) {
		super.setView(view);
		setDefaultsToView();
	}

	private void setDefaultsToView() {
		getView().setModel(toInputModel(getEmployeeName(), getDefaultDate()));
	}

	private String getEmployeeName() {
		GetEmployeeUseCase getEmployeeUseCase = getEmployeeUseCaseFactory.getEmployeeUseCase();
		getEmployeeUseCase.execute(new GetEmployeeRequest(employeeId));
		return getEmployeeUseCase.getResponse().employeeForGetEmployeeResponse.name;
	}

	private LocalDate getDefaultDate() {
		return LocalDate.now();
	}

	private AddTimeCardViewInputModel toInputModel(String employeeName, LocalDate defaultDate) {
		AddTimeCardViewInputModel inputModel = new AddTimeCardViewInputModel();
		inputModel.employeeName = employeeName;
		inputModel.defaultDate = defaultDate; 
		return inputModel;
	}

	@Override
	public void onCancel() {
		close();
	}

	@Override
	public void onAdd() {
		// TODO Auto-generated method stub
		AddTimeCardViewOutputModel model = getView().getModel();
		System.out.println();
		addTimeCardUseCaseFactory.addTimeCardUseCase().execute(new AddTimeCardRequest(
				employeeId, model.date, model.workingHourQty.get()));
		eventBus.post(new AddedTimeCardEvent(getEmployeeName(), model.date));
		close();
	}

	public static interface AddTimeCardControllerFactory {
		AddTimeCardController create(int employeeId);
	}
	
}
