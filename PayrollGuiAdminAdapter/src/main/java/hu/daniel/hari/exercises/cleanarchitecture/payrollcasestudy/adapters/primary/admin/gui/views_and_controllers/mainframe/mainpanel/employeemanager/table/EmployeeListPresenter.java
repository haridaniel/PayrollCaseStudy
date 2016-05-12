package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.table;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.common.SmartDateFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response.AffiliationResponseToEnumConverter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response.PaymentTypeResponseToEnumConverter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response.PaymentTypeResponseToStringFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListView.EmployeeListViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.EmployeeListResponse.EmployeeForEmployeeListResponse;

class EmployeeListPresenter {
	
	private EmployeeListResponse response;
	private SmartDateFormatter smartDateFormatter;
	
	private PaymentTypeResponseToStringFormatter paymentTypeResponseToStringFormatter = new PaymentTypeResponseToStringFormatter();
	private PaymentTypeResponseToEnumConverter paymentTypeResponseToEnumConverter = new PaymentTypeResponseToEnumConverter();
	private AffiliationResponseToEnumConverter affiliationResponseToEnumConverter = new AffiliationResponseToEnumConverter();

	public EmployeeListPresenter(LocalDate currentDate, EmployeeListResponse response) {
		this.response = response;
		smartDateFormatter = new SmartDateFormatter(currentDate);
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
		employeeViewItem.waging = employeeItem.paymentTypeResponse.accept(paymentTypeResponseToStringFormatter);
		employeeViewItem.nextPayDay = smartDateFormatter.format(employeeItem.nextPayDay);
		employeeViewItem.paymentType = employeeItem.paymentTypeResponse.accept(paymentTypeResponseToEnumConverter);
		employeeViewItem.affiliationType = affiliationResponseToEnumConverter.toAffiliationType(employeeItem.affiliationTypeResponse);
		return employeeViewItem;
	}

}