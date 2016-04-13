package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.paymentmethod.PaymentMethodTypeResponseFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.paymenttype.PaymentTypeResponseFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValueImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist.PayListView.PayListViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist.PayListView.PayListViewModel.PayListViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase.PaymentFulfillUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase.PayListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.PayListResponse.PayListResponseItem;

public class PayListController implements ChangeListener<LocalDate> {

	private PayListView view;
	private PayListUseCaseFactory payListUseCaseFactory;
	private PayListPresenter payListPresenter = new PayListPresenter();
	private ObservableValue<LocalDate> observableCurrentDate;
	private ObservableValueImpl<PayListState> observablePayListState = new ObservableValueImpl<PayListState>(new PayListState(0, true));

	@Inject
	public PayListController(
			PayListUseCaseFactory payListUseCaseFactory,
			PaymentFulfillUseCaseFactory paymentFulfillUseCaseFactory
			) {
		this.payListUseCaseFactory = payListUseCaseFactory;
	}

	public void setView(PayListView view) {
		this.view = view;
	}
	
	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		this.observableCurrentDate = observableCurrentDate;
		observableCurrentDate.addChangeListener(this);
	}
	
	public ObservableValue<PayListState> getObservablePayListState() {
		return observablePayListState;
	}

	@Override
	public void onChanged(LocalDate currentDate) {
		updateList(getPayListResponse());
	}

	private PayListResponse getPayListResponse() {
		PayListUseCase useCase = payListUseCaseFactory.payListUseCase();
		useCase.execute(new PayListRequest(observableCurrentDate.get()));
		return useCase.getResponse();
	}

	private void updateList(PayListResponse payListResponse) {
		view.setModel(payListPresenter.toViewModel(payListResponse));
		observablePayListState.set(new PayListState(
				payListResponse.payListResponseItems.size(),
				payListResponse.payListResponseItems.isEmpty()
				));
	}

	private static class PayListPresenter {
		private PaymentTypeResponseFormatter paymentTypeResponseFormatter = new PaymentTypeResponseFormatter();
		private PaymentMethodTypeResponseFormatter paymentMethodTypeResponseFormatter = new PaymentMethodTypeResponseFormatter();

		public PayListViewModel toViewModel(PayListResponse payListResponse) {
			return new PayListViewModel(toViewModel(payListResponse.payListResponseItems));
		}
	
		private List<PayListViewItem> toViewModel(List<PayListResponseItem> payChecks) {
			return payChecks.stream()
					.map(payCheck -> toViewModel(payCheck))
					.collect(Collectors.toList());
		}
	
		private PayListViewItem toViewModel(PayListResponseItem payListResponseItem) {
			PayListViewItem payListViewItem = new PayListViewItem();
			payListViewItem.id = payListResponseItem.employeeId;
			payListViewItem.waging = payListResponseItem.paymentTypeResponse.accept(paymentTypeResponseFormatter);
			payListViewItem.name = payListResponseItem.name;
			payListViewItem.grossAmount = payListResponseItem.grossAmount;
			payListViewItem.deductionsAmount = payListResponseItem.deductionsAmount;
			payListViewItem.netAmount = payListResponseItem.netAmount;
			payListViewItem.paymentMethod = paymentMethodTypeResponseFormatter.format(payListResponseItem.paymentMethodTypeResponse);
			return payListViewItem;
		}
	
	}

}
