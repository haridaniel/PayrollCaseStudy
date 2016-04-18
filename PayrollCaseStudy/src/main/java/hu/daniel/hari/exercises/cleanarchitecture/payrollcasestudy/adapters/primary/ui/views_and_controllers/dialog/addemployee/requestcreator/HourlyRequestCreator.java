package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;

public class HourlyRequestCreator extends RequestCreator<HourlyEmployeeViewModel, AddHourlyEmployeeRequest> {
	@Override
	protected AddHourlyEmployeeRequest toSpecificRequest(HourlyEmployeeViewModel model) {
		AddHourlyEmployeeRequest addHourlyEmployeeRequest = new AddHourlyEmployeeRequest();
		addHourlyEmployeeRequest.hourlyWage = model.hourlyWage;
		return addHourlyEmployeeRequest;
	}
}