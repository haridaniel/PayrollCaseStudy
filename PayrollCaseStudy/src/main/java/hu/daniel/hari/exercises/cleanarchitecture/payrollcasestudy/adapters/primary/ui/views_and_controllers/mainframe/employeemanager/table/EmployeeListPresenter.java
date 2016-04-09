package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.date.NextPaydayDateFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.paymenttype.PaymentTypeResponseFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse.EmployeeForEmployeeListResponse;

class EmployeeListPresenter {

	private EmployeeListResponse response;
	private NextPaydayDateFormatter nextPaydayDateFormatter;
	private PaymentTypeResponseFormatter paymentTypeResponseFormatter = new PaymentTypeResponseFormatter();

	public EmployeeListPresenter(LocalDate currentDate, EmployeeListResponse response) {
		this.response = response;
		nextPaydayDateFormatter = new NextPaydayDateFormatter(currentDate);
	}

	public EmployeeListViewModel toViewModel() {
		return new EmployeeListViewModel(toViewModel(response.employees));
	}

	private List<EmployeeViewItem> toViewModel(List<EmployeeForEmployeeListResponse> employeeItems) {
		return employeeItems.stream()
				.map(employeeItem -> toViewModel(employeeItem))
				.collect(Collectors.toList());
	}

	private EmployeeViewItem toViewModel(EmployeeForEmployeeListResponse employeeItem) {
		EmployeeViewItem employeeViewItem = new EmployeeViewItem();
		employeeViewItem.id = employeeItem.id;
		employeeViewItem.name = employeeItem.name;
		employeeViewItem.address = employeeItem.address;
		employeeViewItem.waging = employeeItem.paymentTypeResponse.accept(paymentTypeResponseFormatter);
		employeeViewItem.nextPayDay = nextPaydayDateFormatter.format(employeeItem.nextPayDay);
		return employeeViewItem;
	}

}