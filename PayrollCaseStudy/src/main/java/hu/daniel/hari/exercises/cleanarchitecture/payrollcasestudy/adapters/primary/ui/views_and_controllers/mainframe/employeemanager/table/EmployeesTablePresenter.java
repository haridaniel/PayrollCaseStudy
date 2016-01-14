package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.date.NextPaydayDateFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.paymenttype.WagingFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableView.EmployeesTableViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableView.EmployeesTableViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse.EmployeeForEmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse;

class EmployeesTablePresenter {

	private WagingFormatter wagingFormatter = new WagingFormatter();
	private NextPaydayDateFormatter nextPaydayDateFormatter = new NextPaydayDateFormatter(currentDate());

	private LocalDate currentDate() {
		return LocalDate.now();
	}

	public EmployeesTableViewModel toViewModel(EmployeeListResponse response) {
		return new EmployeesTableViewModel(toViewModel(response.employees));
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
		employeeViewItem.waging = employeeItem.paymentTypeResponse.accept(wagingFormatter);
		employeeViewItem.nextPayDay = nextPaydayDateFormatter.format(employeeItem.nextPayDay);
		return employeeViewItem;
	}

}