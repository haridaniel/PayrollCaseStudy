package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay.paylist;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.PersistentDataChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.usecase.response.PaymentMethodTypeResponseToStringFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.usecase.response.PaymentTypeResponseToStringFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Controller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay.paylist.PayListView.PayListViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay.paylist.PayListView.PayListViewModel.PayListViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.PayListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse.PayListResponseItem;

public class PayListController implements
	Controller<PayListView>,
	ChangeListener<LocalDate> 
{
	private PayListUseCaseFactory payListUseCaseFactory;
	private PayListView view;
	private PayListPresenter payListPresenter;
	private Observable<LocalDate> observableCurrentDate;
	private ObservableValue<PayListState> observablePayListState = new ObservableValue<PayListState>(new PayListState(0, true));

	@Inject
	public PayListController(
			PayListUseCaseFactory payListUseCaseFactory,
			PayListPresenter listPresenter,
			EventBus eventBus
			) {
		this.payListUseCaseFactory = payListUseCaseFactory;
		payListPresenter = listPresenter;
		eventBus.register(this);
	}

	public void setView(PayListView view) {
		this.view = view;
	}
	
	public void setObservableCurrentDate(Observable<LocalDate> observableCurrentDate) {
		this.observableCurrentDate = observableCurrentDate;
		observableCurrentDate.addChangeListener(this);
	}
	
	public Observable<PayListState> getObservablePayListState() {
		return observablePayListState;
	}

	@Subscribe
	public void on(PersistentDataChangedEvent e) {
		update();
	}

	@Override
	public void onChanged(LocalDate currentDate) {
		update();
	}

	private void update() {
		update(getPayListResponse());
	}

	private PayListResponse getPayListResponse() {
		return payListUseCaseFactory.payListUseCase().execute(new PayListRequest(observableCurrentDate.get()));
	}

	private void update(PayListResponse payListResponse) {
		view.setModel(payListPresenter.toViewModel(payListResponse));
		observablePayListState.set(new PayListState(
				payListResponse.payListResponseItems.size(),
				payListResponse.payListResponseItems.isEmpty()
				));
	}

	private static class PayListPresenter {
		private PaymentTypeResponseToStringFormatter paymentTypeResponseToStringFormatter;
		private PaymentMethodTypeResponseToStringFormatter paymentMethodTypeResponseToStringFormatter;
		
		@Inject
		public PayListPresenter(
				PaymentTypeResponseToStringFormatter paymentTypeResponseToStringFormatter,
				PaymentMethodTypeResponseToStringFormatter paymentMethodTypeResponseToStringFormatter
				) {
			this.paymentTypeResponseToStringFormatter = paymentTypeResponseToStringFormatter;
			this.paymentMethodTypeResponseToStringFormatter = paymentMethodTypeResponseToStringFormatter;
		}
		
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
			payListViewItem.waging = payListResponseItem.paymentTypeResponse.accept(paymentTypeResponseToStringFormatter);
			payListViewItem.name = payListResponseItem.name;
			payListViewItem.grossAmount = payListResponseItem.grossAmount;
			payListViewItem.deductionsAmount = payListResponseItem.deductionsAmount;
			payListViewItem.netAmount = payListResponseItem.netAmount;
			payListViewItem.paymentMethod = paymentMethodTypeResponseToStringFormatter.format(payListResponseItem.paymentMethodTypeResponse);
			return payListViewItem;
		}
	
	}

}
