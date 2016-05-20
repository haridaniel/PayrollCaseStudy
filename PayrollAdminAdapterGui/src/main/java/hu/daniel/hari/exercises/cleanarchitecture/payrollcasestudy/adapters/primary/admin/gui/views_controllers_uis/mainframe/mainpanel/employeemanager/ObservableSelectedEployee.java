package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager;

import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse.EmployeeForEmployeeListResponse;

public interface ObservableSelectedEployee extends Observable<Optional<EmployeeForEmployeeListResponse>> {
}
