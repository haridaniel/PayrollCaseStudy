package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;
import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.confirm.ConfirmMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidationErrorPresenter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.AbstractDialogViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewInputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewOutputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI.ConfirmDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase.TimeCardAlreadyExistsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;

public class AddTimeCardController extends 
	AbstractDialogViewController<AddTimeCardView, AddTimeCardViewListener> implements 
	AddTimeCardViewListener 
{
	private EventBus eventBus;
	private int employeeId;
	private GetEmployeeUseCaseFactory getEmployeeUseCaseFactory;
	private AddTimeCardUseCaseFactory addTimeCardUseCaseFactory;
	private Provider<ConfirmDialogUI> confirmDialogUIProvider;
	private ConfirmMessageFormatter confirmMessageFormatter;

	@Inject
	public AddTimeCardController(
			EventBus eventBus,
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory,
			AddTimeCardUseCaseFactory addTimeCardUseCaseFactory,
			Provider<ConfirmDialogUI> confirmDialogUIProvider,
			ConfirmMessageFormatter confirmMessageFormatter,
			@Assisted int employeeId
			) {
		super(eventBus);
		this.eventBus = eventBus;
		this.getEmployeeUseCaseFactory = getEmployeeUseCaseFactory;
		this.addTimeCardUseCaseFactory = addTimeCardUseCaseFactory;
		this.confirmDialogUIProvider = confirmDialogUIProvider;
		this.confirmMessageFormatter = confirmMessageFormatter;
		this.employeeId = employeeId;
	}
	
	@Override
	protected boolean onCloseActionIsAllowed() {
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
		AddTimeCardViewOutputModel model = getView().getModel();
		try {
			validate(model);
			addTimeCardUseCaseFactory.addTimeCardUseCase().execute(toRequest(model, false));
			onAdded(model);
		} catch (FieldValidatorException e) {
			getView().setValidationErrorMessagesModel(new FieldValidationErrorPresenter().present(e));
		} catch (TimeCardAlreadyExistsException e) {
			confirmDialogUIProvider.get().show(confirmMessageFormatter.timeCardAlreadyExists(), new ConfirmDialogListener() {
				@Override
				public void onOk() {
					addTimeCardUseCaseFactory.addTimeCardUseCase().execute(toRequest(model, true));
					onAdded(model);
				}
			});
		} 
	}

	private void onAdded(AddTimeCardViewOutputModel model) {
		eventBus.post(new AddedTimeCardEvent(getEmployeeName(), model.date));
		close();
	}

	private AddTimeCardRequest toRequest(AddTimeCardViewOutputModel model, boolean forceOverwrite) {
		return new AddTimeCardRequest(employeeId, model.date, model.workingHourQty.get(), forceOverwrite);
	}


	private void validate(AddTimeCardViewOutputModel model) {
		throwIfThereAreErrors(new AddTimeCardFieldsValidator().getErrors(model));
	}
	private void throwIfThereAreErrors(List<FieldValidatorError> fieldValidatorErrors) {
		if(!fieldValidatorErrors.isEmpty())
			throw new FieldValidatorException(fieldValidatorErrors);
	}

	public static interface AddTimeCardControllerFactory {
		AddTimeCardController create(int employeeId);
	}

	@Override
	protected AddTimeCardViewListener getViewListener() {
		return this;
	}
	
}
