package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.EmployeesOverviewPanelView.EmployeesOverviewPanelListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.EmployeesOverviewUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.EmployeesOverviewUseCase.EmployeesOverviewUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse.EmployeeItem;

public class EmployeesOverviewPanelPresenter implements EmployeesOverviewPanelListener {
	
	private EmployeesOverviewPanelView view;
	private EmployeesOverviewUseCaseFactory useCaseFactory;

	public EmployeesOverviewPanelPresenter(EmployeesOverviewPanelView view, EmployeesOverviewUseCaseFactory useCaseFactory) {
		this.view = view;
		this.useCaseFactory = useCaseFactory;
		view.setListener(this);
	}

	@Override
	public void onViewAdded() {
		updateTable();
	}

	private void updateTable() {
		EmployeesOverviewUseCase useCase = useCaseFactory.employeesOverviewUseCase();
		useCase.execute(new EmptyRequest());
		view.setModel(toViewModel(useCase.getResponse()));
	}

	private EmployeesOverviewPanelViewModel toViewModel(EmployeesOverviewUseCaseResponse response) {

		Vector<Vector<Object>> data = new Vector<>();
		for (EmployeeItem employeeItem : response.employeeItems) {
			data.add(new Vector<Object>(){{
				add(employeeItem.id);
				add(employeeItem.name);
				add(employeeItem.address);
				add(employeeItem.paymentClassificationType.name());
			}});
		}
		
		Vector<String> columnNames = new Vector<String>() {{
			add("Id");
			add("Name");
			add("Address");
			add("Type");
		}};
		
		return new EmployeesOverviewPanelViewModel(new DefaultTableModel(data, columnNames));
	}
	
}
