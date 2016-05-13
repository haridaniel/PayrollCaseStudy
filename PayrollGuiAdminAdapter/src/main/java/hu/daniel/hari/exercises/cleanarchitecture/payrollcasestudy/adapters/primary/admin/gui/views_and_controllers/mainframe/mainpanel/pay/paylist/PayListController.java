package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response.PaymentMethodTypeResponseToStringFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response.PaymentTypeResponseToStringFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.EmployeeChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.PersistentDataChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.Controller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ObservableValueImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ObservableValue.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListView.PayListViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist.PayListView.PayListViewModel.PayListViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse.PayListResponseItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories.PayListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories.PaymentFulfillUseCaseFactory;

public class PayListController implements
	Controller<PayListView>,
	ChangeListener<LocalDate> 
{
	private PayListView view;
	private PayListUseCaseFactory payListUseCaseFactory;
	private PayListPresenter payListPresenter = new PayListPresenter();
	private ObservableValue<LocalDate> observableCurrentDate;
	private ObservableValueImpl<PayListState> observablePayListState = new ObservableValueImpl<PayListState>(new PayListState(0, true));

	@Inject
	public PayListController(
			PayListUseCaseFactory payListUseCaseFactory,
			PaymentFulfillUseCaseFactory paymentFulfillUseCaseFactory,
			EventBus eventBus
			) {
		this.payListUseCaseFactory = payListUseCaseFactory;
		eventBus.register(this);
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
		PayListUseCase useCase = payListUseCaseFactory.payListUseCase();
		useCase.execute(new PayListRequest(observableCurrentDate.get()));
		return useCase.getResponse();
	}

	private void update(PayListResponse payListResponse) {
		view.setModel(payListPresenter.toViewModel(payListResponse));
		observablePayListState.set(new PayListState(
				payListResponse.payListResponseItems.size(),
				payListResponse.payListResponseItems.isEmpty()
				));
	}

	private static class PayListPresenter {
		private PaymentTypeResponseToStringFormatter paymentTypeResponseToStringFormatter = new PaymentTypeResponseToStringFormatter();
		private PaymentMethodTypeResponseToStringFormatter paymentMethodTypeResponseToStringFormatter = new PaymentMethodTypeResponseToStringFormatter();

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
