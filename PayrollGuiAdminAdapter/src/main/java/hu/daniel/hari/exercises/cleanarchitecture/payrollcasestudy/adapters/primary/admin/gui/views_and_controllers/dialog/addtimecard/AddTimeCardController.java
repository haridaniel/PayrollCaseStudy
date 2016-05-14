package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;
import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.controller.msg.ConfirmMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.UpdatedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidationErrorPresenter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidatorException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.AbstractDialogViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewInputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardView.AddTimeCardViewOutputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI.ConfirmDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.AddTimeCardUseCase.TimeCardAlreadyExistsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard.UpdateTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.UpdateTimeCardUseCaseFactory;

public class AddTimeCardController extends 
	AbstractDialogViewController<AddTimeCardView, AddTimeCardViewListener> implements 
	AddTimeCardViewListener 
{
	private EventBus eventBus;
	private int employeeId;
	private GetEmployeeUseCaseFactory getEmployeeUseCaseFactory;
	private AddTimeCardUseCaseFactory addTimeCardUseCaseFactory;
	private UpdateTimeCardUseCaseFactory updateTimeCardUseCaseFactory;
	private Provider<ConfirmDialogUI> confirmDialogUIProvider;
	private ConfirmMessageFormatter confirmMessageFormatter;

	@Inject
	public AddTimeCardController(
			EventBus eventBus,
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory,
			AddTimeCardUseCaseFactory addTimeCardUseCaseFactory,
			UpdateTimeCardUseCaseFactory updateTimeCardUseCaseFactory,
			Provider<ConfirmDialogUI> confirmDialogUIProvider,
			ConfirmMessageFormatter confirmMessageFormatter,
			@Assisted int employeeId
			) {
		super(eventBus);
		this.eventBus = eventBus;
		this.getEmployeeUseCaseFactory = getEmployeeUseCaseFactory;
		this.addTimeCardUseCaseFactory = addTimeCardUseCaseFactory;
		this.updateTimeCardUseCaseFactory = updateTimeCardUseCaseFactory;
		this.confirmDialogUIProvider = confirmDialogUIProvider;
		this.confirmMessageFormatter = confirmMessageFormatter;
		this.employeeId = employeeId;
	}
	
	@Override
	protected boolean onCloseAction_shouldCloseAutomatically() {
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
		return getEmployeeUseCaseFactory.getEmployeeUseCase().execute(new GetEmployeeRequest(employeeId))
				.employeeForGetEmployeeResponse.name;
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
			addTimeCardUseCaseFactory.addTimeCardUseCase().execute(toAddRequest(model));
			onAdded(model);
		} catch (FieldValidatorException e) {
			getView().setValidationErrorMessagesModel(new FieldValidationErrorPresenter().present(e));
		} catch (TimeCardAlreadyExistsException e) {
			onTimeCardAlreadyExists(model);
		} 
	}

	private void onTimeCardAlreadyExists(AddTimeCardViewOutputModel model) {
		confirmDialogUIProvider.get().show(confirmMessageFormatter.timeCardAlreadyExists(), new ConfirmDialogListener() {
			@Override
			public void onOk() {
				updateTimeCardUseCaseFactory.updateTimeCardUseCase().execute(toUpdateRequest(model));
				onUpdated(model);
			}
		});
	}

	private void onAdded(AddTimeCardViewOutputModel model) {
		eventBus.post(new AddedTimeCardEvent(getEmployeeName(), model.date));
		close();
	}

	private void onUpdated(AddTimeCardViewOutputModel model) {
		eventBus.post(new UpdatedTimeCardEvent(getEmployeeName(), model.date));
		close();
	}
	
	private AddTimeCardRequest toAddRequest(AddTimeCardViewOutputModel model) {
		return new AddTimeCardRequest(employeeId, model.date, model.workingHourQty.get());
	}
	
	private UpdateTimeCardRequest toUpdateRequest(AddTimeCardViewOutputModel model) {
		return new UpdateTimeCardRequest(employeeId, model.date, model.workingHourQty.get());
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
