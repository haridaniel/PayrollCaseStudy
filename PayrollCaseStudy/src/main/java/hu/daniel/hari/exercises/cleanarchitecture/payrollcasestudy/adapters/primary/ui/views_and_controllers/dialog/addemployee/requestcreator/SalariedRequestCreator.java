package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class SalariedRequestCreator extends RequestCreator<SalariedEmployeeViewModel, AddSalariedEmployeeRequest> {
	@Override
	protected AddSalariedEmployeeRequest toSpecificRequest(SalariedEmployeeViewModel model) {
		AddSalariedEmployeeRequest addSalariedEmployeeRequest = new AddSalariedEmployeeRequest();
		addSalariedEmployeeRequest.monthlySalary = model.monthlySalary;
		return addSalariedEmployeeRequest;
	}
}