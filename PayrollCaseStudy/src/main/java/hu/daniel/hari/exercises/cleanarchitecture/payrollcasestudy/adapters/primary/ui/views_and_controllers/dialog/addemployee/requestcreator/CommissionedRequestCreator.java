package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.CommissionedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.addemployee.AddCommissionedEmployeeRequest;

public class CommissionedRequestCreator extends RequestCreator<CommissionedEmployeeViewModel, AddCommissionedEmployeeRequest> {
	@Override
	protected AddCommissionedEmployeeRequest toSpecificRequest(CommissionedEmployeeViewModel model) {
		AddCommissionedEmployeeRequest request = new AddCommissionedEmployeeRequest();
		request.biWeeklyBaseSalary = model.biWeeklyBaseSalary;
		request.commissionRate = model.commissionRatePercentage / 100d;
		return request;
	}
}