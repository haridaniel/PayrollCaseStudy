package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.PayDayView.PayDayViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist.PayCheckListView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist.PayCheckListView.PayCheckListViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist.PayCheckListView.PayCheckListViewModel.PayCheckViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.generate.GeneratePayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.generate.GeneratePayUseCase.GeneratePayUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.SendPayUseCase.SendPayUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GeneratePayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GeneratePayResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GeneratePayResponse.PayCheckResponse;

public class PayDayController implements PayDayViewListener {

	private PayDayView view;
	private PayCheckListView payCheckListView;
	private GeneratePayUseCaseFactory generatePayUseCaseFactory;
	private SendPayUseCaseFactory sendPayUseCaseFactory;
	private Presenter presenter = new Presenter();

	private LocalDate payDate;

	@Inject
	public PayDayController(
			GeneratePayUseCaseFactory generatePayUseCaseFactory,
			SendPayUseCaseFactory sendPayUseCaseFactory
			) {
		this.generatePayUseCaseFactory = generatePayUseCaseFactory;
		this.sendPayUseCaseFactory = sendPayUseCaseFactory;
//		payDate = LocalDate.now();
		payDate = LocalDate.of(2016, 4, 8);
	}

	public void setView(PayDayView view) {
		this.view = view;
		payCheckListView = view.getPayCheckListView();
	}
	
	@Override
	public void onSendPayAction() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onInitialized() {
		updateCheckList();
	}

	private void updateCheckList() {
		payCheckListView.setModel(presenter.toViewModel(generatePay()));
	}

	private GeneratePayResponse generatePay() {
		GeneratePayUseCase useCase = generatePayUseCaseFactory.generatePayUseCase();
		useCase.execute(new GeneratePayRequest(payDate));
		return useCase.getResponse();
	}

	private static class Presenter {

		public PayCheckListViewModel toViewModel(GeneratePayResponse generatePayResponse) {
			return new PayCheckListViewModel(toViewModel(generatePayResponse.payCheckResponses));
		}

		private List<PayCheckViewModel> toViewModel(List<PayCheckResponse> payChecks) {
			return payChecks.stream()
					.map(payCheck -> toViewModel(payCheck))
					.collect(Collectors.toList());
		}

		private PayCheckViewModel toViewModel(PayCheckResponse payCheckResponse) {
			PayCheckViewModel payCheckViewModel = new PayCheckViewModel();
			payCheckViewModel.id = payCheckResponse.employeeId;
			payCheckViewModel.name = payCheckResponse.name;
			payCheckViewModel.grossAmount = payCheckResponse.grossAmount;
			payCheckViewModel.deductionsAmount = payCheckResponse.deductionsAmount;
			payCheckViewModel.netAmount = payCheckResponse.netAmount;
			return payCheckViewModel;
		}

	}

}
