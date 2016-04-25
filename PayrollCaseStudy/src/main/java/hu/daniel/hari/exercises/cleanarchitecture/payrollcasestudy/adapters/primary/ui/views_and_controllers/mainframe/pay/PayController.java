package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.confirm.ConfirmMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.PaymentsFulfilledEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.ConfirmDialogUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI.ConfirmDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.PayView.PayViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.PayView.PayViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist.PayListState;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase.PaymentFulfillUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.PaymentFulfillRequest;

public class PayController extends 
	AbstractController<PayView, PayViewListener> implements
	PayViewListener, 
	ChangeListener<PayListState> 
{

	private PaymentFulfillUseCaseFactory paymentFulfillUseCaseFactory;
	private ObservableValue<LocalDate> observableCurrentDate;
	private Provider<ConfirmDialogUI> confirmDialogUIProvider;
	private ConfirmMessageFormatter confirmMessageFormatter;
	private EventBus eventBus;
	private PayListState payListState;

	@Inject
	public PayController(
			PaymentFulfillUseCaseFactory paymentFulfillUseCaseFactory,
			Provider<ConfirmDialogUI> confirmDialogUIProvider,
			ConfirmMessageFormatter confirmMessageFormatter,
			EventBus eventBus
			) {
		this.paymentFulfillUseCaseFactory = paymentFulfillUseCaseFactory;
		this.confirmDialogUIProvider = confirmDialogUIProvider;
		this.confirmMessageFormatter = confirmMessageFormatter;
		this.eventBus = eventBus;
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		this.observableCurrentDate = observableCurrentDate;
	}

	@Override
	public void onFulfillPayAction() {
		confirmDialogUIProvider.get().show(confirmMessageFormatter.fulfillPayments(payListState.itemCount), new ConfirmDialogListener() {
			@Override
			public void onOk() {
				PaymentFulfillUseCase useCase = paymentFulfillUseCaseFactory.paymentFulfillUseCase();
				useCase.execute(new PaymentFulfillRequest(observableCurrentDate.get()));
				eventBus.post(new PaymentsFulfilledEvent(observableCurrentDate.get(), 
						useCase.getResponse().payCheckCount, useCase.getResponse().totalGrossAmount));
			}
		});
	}

	@Override
	public void onChanged(PayListState payListState) {
		this.payListState = payListState;
		view.setModel(present(payListState));
	}

	private PayViewModel present(PayListState payListState) {
		return new PayViewModel() {{
			isFulfillButtonEnabled = !payListState.isEmpty;
		}};
	}

	@Override
	protected PayViewListener getViewListener() {
		return this;
	}

}
