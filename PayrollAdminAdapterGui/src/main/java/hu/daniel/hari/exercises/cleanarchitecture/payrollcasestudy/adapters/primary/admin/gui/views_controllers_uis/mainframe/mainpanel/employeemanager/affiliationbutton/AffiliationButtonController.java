package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.affiliationbutton;

import java.util.Optional;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.AffiliationChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberUI.AddUnionMemberUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.ObservableSelectedEployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonView.AffiliationButtonViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonView.AffiliationButtonViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.GetUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse.EmployeeForEmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.AffiliationTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;

public class AffiliationButtonController extends 
	AbstractController<AffiliationButtonView, AffiliationButtonViewListener> implements 
	AffiliationButtonViewListener, 
	ChangeListener<Optional<EmployeeForEmployeeListResponse>>
{
	private EventBus eventBus;
	private ObservableSelectedEployee observableSelectedEmployee;
	private GetUnionMemberAffiliationUseCaseFactory getUnionMemberAffiliationUseCaseFactory;
	private RemoveUnionMemberAffiliationUseCaseFactory removeUnionMemberAffiliationUseCaseFactory;
	private AddUnionMemberUIFactory addUnionMemberUIFactory;

	@Inject
	public AffiliationButtonController(
			GetUnionMemberAffiliationUseCaseFactory getUnionMemberAffiliationUseCaseFactory,
			RemoveUnionMemberAffiliationUseCaseFactory removeUnionMemberAffiliationUseCaseFactory,
			AddUnionMemberUIFactory addUnionMemberUIFactory,
			EventBus eventBus
			) {
		this.getUnionMemberAffiliationUseCaseFactory = getUnionMemberAffiliationUseCaseFactory;
		this.removeUnionMemberAffiliationUseCaseFactory = removeUnionMemberAffiliationUseCaseFactory;
		this.addUnionMemberUIFactory = addUnionMemberUIFactory;
		this.eventBus = eventBus;
	}

	@Override
	protected AffiliationButtonViewListener getViewListener() {
		return this;
	}

	@Override
	public void onActionPerformed() {
		EmployeeForEmployeeListResponse employee = observableSelectedEmployee.get().get();
		Action action = toAction(employee.affiliationTypeResponse);
		action.execute(employee);
		update();
	}

	public void setObservableSelectedEmployee(ObservableSelectedEployee observableSelectedEployee) {
		this.observableSelectedEmployee = observableSelectedEployee;
		observableSelectedEployee.addChangeListener(this);
	}

	@Override
	public void onChanged(Optional<EmployeeForEmployeeListResponse> employee) {
		update();
	}

	private void update() {
		getView().setModel(present(observableSelectedEmployee.get()));
	}

	private AffiliationButtonViewModel present(Optional<EmployeeForEmployeeListResponse> employee) {
		AffiliationButtonViewModel model = new AffiliationButtonViewModel();
		model.enabled = employee.isPresent();
		model.buttonText = employee.map(e -> toButtonText(e.affiliationTypeResponse)).orElse("Affiliation..");
		return model;
	}
	
	private String toButtonText(AffiliationTypeResponse affiliationType) {
		return toAction(affiliationType).getButtonText();
	}

	private Action toAction(AffiliationTypeResponse affiliationType) {
		switch (affiliationType) {
		case UNION_MEMBER:
			return new RemoveUnionMemberAction();
		case NO:
			return new ChangeToUnionMemberAction();
		default:
			throw new RuntimeException("not implemented");
		}
	}

	private static interface Action {
		void execute(EmployeeForEmployeeListResponse e);
		String getButtonText();
	}

	private class ChangeToUnionMemberAction implements Action {
		@Override
		public void execute(EmployeeForEmployeeListResponse e) {
			addUnionMemberUIFactory.create(e.id).show();
		}
		@Override
		public String getButtonText() {
			return "Change to Union Member..";
		}

	}
	private class RemoveUnionMemberAction implements Action {
		@Override
		public void execute(EmployeeForEmployeeListResponse e) {
			removeUnionMemberAffiliationUseCaseFactory.removeUnionMemberAffiliationUseCase().execute(
					new RemoveUnionMemberAffiliationRequest(getUnionMemberAffiliation(e).unionMemberId));
			eventBus.post(new AffiliationChangedEvent());
		}
		private GetUnionMemberAffiliationResponse getUnionMemberAffiliation(EmployeeForEmployeeListResponse e) {
			return getUnionMemberAffiliationUseCaseFactory.getUnionMemberAffiliationUseCase()
					.execute(new GetUnionMemberAffiliationRequest(e.id));
		}
		@Override
		public String getButtonText() {
			return "Remove Affiliation";
		}

	}

}
