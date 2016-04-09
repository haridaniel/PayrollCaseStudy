package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.PayView.PayViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.SendPayUseCase.SendPayUseCaseFactory;

public class PayController implements PayViewListener {

	private PayView view;
	private SendPayUseCaseFactory sendPayUseCaseFactory;
	private ObservableValue<LocalDate> observableCurrentDate;

	@Inject
	public PayController(
			SendPayUseCaseFactory sendPayUseCaseFactory
			) {
		this.sendPayUseCaseFactory = sendPayUseCaseFactory;
	}

	public void setView(PayView view) {
		this.view = view;
	}
	
	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		this.observableCurrentDate = observableCurrentDate;
	}

	@Override
	public void onSendPayAction() {
		// TODO Auto-generated method stub
	}

}
