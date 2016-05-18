package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.requestcreator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddEmployeeRequest;

public abstract class RequestCreator<I extends EmployeeViewModel, O extends AddEmployeeRequest> {
	public O toRequest(I model) {
		return fill(model, toSpecificRequest(model));
	}

	private O fill(I model, O request) {
		request.employeeId = model.employeeId.get();
		request.name = model.name;
		request.address = model.address;
		return request;
	}
	
	protected abstract O toSpecificRequest(I model);
}