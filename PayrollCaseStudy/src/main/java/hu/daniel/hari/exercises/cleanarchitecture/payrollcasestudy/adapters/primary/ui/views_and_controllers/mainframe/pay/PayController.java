package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.PayView.PayViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.PayView.PayViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist.PayListState;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase.PaymentFulfillUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.PaymentFulfillRequest;

public class PayController implements PayViewListener, ChangeListener<PayListState> {

	private PayView view;
	private PaymentFulfillUseCaseFactory paymentFulfillUseCaseFactory;
	private ObservableValue<LocalDate> observableCurrentDate;

	@Inject
	public PayController(
			PaymentFulfillUseCaseFactory paymentFulfillUseCaseFactory
			) {
		this.paymentFulfillUseCaseFactory = paymentFulfillUseCaseFactory;
	}

	public void setView(PayView view) {
		this.view = view;
	}
	
	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		this.observableCurrentDate = observableCurrentDate;
	}

	@Override
	public void onFulfillPayAction() {
		paymentFulfillUseCaseFactory.paymentFulfillUseCase().execute(new PaymentFulfillRequest(observableCurrentDate.get()));
	}

	@Override
	public void onChanged(PayListState payListState) {
		view.setModel(present(payListState));
	}

	private PayViewModel present(PayListState payListState) {
		return new PayViewModel() {{
			isFulfillButtonEnabled = !payListState.isEmpty;
		}};
	}

}
