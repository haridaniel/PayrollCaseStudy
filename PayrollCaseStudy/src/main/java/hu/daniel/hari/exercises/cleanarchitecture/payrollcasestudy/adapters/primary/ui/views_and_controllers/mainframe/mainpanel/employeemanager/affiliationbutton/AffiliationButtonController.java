package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton;

import java.util.Optional;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AffiliationChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValue.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.ObservableSelectedEployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem.AffiliationType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonView.AffiliationButtonViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonView.AffiliationButtonViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.AddUnionMemberAffiliationUseCase.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.GetUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.GetUnionMemberAffiliationUseCase.GetUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.RemoveUnionMemberAffiliationUseCase.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.affiliation.RemoveUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.affiliation.unionmember.GetUnionMemberAffiliationResponse;

public class AffiliationButtonController extends 
	AbstractController<AffiliationButtonView, AffiliationButtonViewListener> implements 
	AffiliationButtonViewListener, 
	ChangeListener<Optional<EmployeeViewItem>>
{
	private ObservableSelectedEployeeItem observableSelectedEmployee;
	private GetUnionMemberAffiliationUseCaseFactory getUnionMemberAffiliationUseCaseFactory;
	private AddUnionMemberAffiliationUseCaseFactory addUnionMemberAffiliationUseCaseFactory;
	private RemoveUnionMemberAffiliationUseCaseFactory removeUnionMemberAffiliationUseCaseFactory;
	private EventBus eventBus;

	@Inject
	public AffiliationButtonController(
			GetUnionMemberAffiliationUseCaseFactory getUnionMemberAffiliationUseCaseFactory,
			AddUnionMemberAffiliationUseCaseFactory addUnionMemberAffiliationUseCaseFactory,
			RemoveUnionMemberAffiliationUseCaseFactory removeUnionMemberAffiliationUseCaseFactory,
			EventBus eventBus
			) {
		this.getUnionMemberAffiliationUseCaseFactory = getUnionMemberAffiliationUseCaseFactory;
		this.addUnionMemberAffiliationUseCaseFactory = addUnionMemberAffiliationUseCaseFactory;
		this.removeUnionMemberAffiliationUseCaseFactory = removeUnionMemberAffiliationUseCaseFactory;
		this.eventBus = eventBus;
	}

	@Override
	protected AffiliationButtonViewListener getViewListener() {
		return this;
	}

	@Override
	public void onActionPerformed() {
		EmployeeViewItem employee = observableSelectedEmployee.get().get();
		Action action = toAction(employee.affiliationType);
		action.execute(employee);
		eventBus.post(new AffiliationChangedEvent());
		update();
	}

	public void setObservableSelectedEmployee(ObservableSelectedEployeeItem observableSelectedEployee) {
		this.observableSelectedEmployee = observableSelectedEployee;
		observableSelectedEployee.addChangeListener(this);
	}

	@Override
	public void onChanged(Optional<EmployeeViewItem> employee) {
		update();
	}

	private void update() {
		getView().setModel(present(observableSelectedEmployee.get()));
	}

	private AffiliationButtonViewModel present(Optional<EmployeeViewItem> employee) {
		AffiliationButtonViewModel model = new AffiliationButtonViewModel();
		model.enabled = employee.isPresent();
		model.buttonText = employee.map(e -> toButtonText(e.affiliationType)).orElse("Affiliation..");
		return model;
	}

	private String toButtonText(AffiliationType affiliationType) {
		return toAction(affiliationType).getButtonText();
	}

	private Action toAction(AffiliationType affiliationType) {
		switch (affiliationType) {
		case UNION_MEMBER:
			return new RemoveUnionMemberAction();
		case NONE:
			return new ChangeToUnionMemberAction();
		default:
			throw new RuntimeException("not implemented");
		}
	}

	private static interface Action {
		void execute(EmployeeViewItem e);
		String getButtonText();
	}

	private class ChangeToUnionMemberAction implements Action {
		@Override
		public void execute(EmployeeViewItem e) {
			int unionMemberId = 6000;
			int weeklyDueAmount = 500;
			addUnionMemberAffiliationUseCaseFactory.addUnionMemberAffiliationUseCase().execute(new AddUnionMemberAffiliationRequest(e.id, unionMemberId, weeklyDueAmount));
		}
		//TODO: internationalize
		@Override
		public String getButtonText() {
			return "Change to Union Member";
		}

	}
	private class RemoveUnionMemberAction implements Action {
		@Override
		public void execute(EmployeeViewItem e) {
			//TODO: HERE we call new usecase 
			GetUnionMemberAffiliationUseCase unionMemberAffiliationUseCase = getUnionMemberAffiliationUseCaseFactory.getUnionMemberAffiliationUseCase();
			unionMemberAffiliationUseCase.execute(new GetUnionMemberAffiliationRequest(e.id));
			GetUnionMemberAffiliationResponse unionMemberAffiliationResponse = unionMemberAffiliationUseCase.getResponse();
			int unionMemberId = unionMemberAffiliationResponse.unionMemberId;
			
			removeUnionMemberAffiliationUseCaseFactory.removeUnionMemberAffiliationUseCase().execute(new RemoveUnionMemberAffiliationRequest(unionMemberId));
		}
		@Override
		public String getButtonText() {
			return "Remove Affiliation";
		}

	}

}
