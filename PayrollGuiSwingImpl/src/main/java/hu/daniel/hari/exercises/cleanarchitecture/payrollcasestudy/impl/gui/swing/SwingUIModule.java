package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.UseCaseFactoriesForGUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardUI.AddTimeCardUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addtimecard.AddTimeCardView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberController.AddUnionMemberControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberUI.AddUnionMemberUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.error.ErrorDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.MainFrameUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.error.UncaugthExceptionHandler;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.util.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.AddEmployeeUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addtimecard.AddTimeCardUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addunionmemberaffiliation.AddUnionMemberUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.common.ConfirmDialogUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.common.ErrorDialogUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.ChangeToAbstractPaymentMethodUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.EmployeeListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.GetUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.PayListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.PaymentFulfillUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.UpdateTimeCardUseCaseFactory;

public class SwingUIModule extends AbstractModule {
	private UseCaseFactoriesForGUI useCaseFactories;

	public SwingUIModule(UseCaseFactoriesForGUI useCaseFactories) {
		this.useCaseFactories = useCaseFactories;
	}

	@Override
	protected void configure() {
		bind(EventBus.class).to(EventQueueAsyncEventBus.class).asEagerSingleton();
		bind(UncaugthExceptionHandler.class).asEagerSingleton();
		bindUseCaseFactories();
		bindUIs();
		installAssistedFactories();
	}

	private void bindUseCaseFactories() {
		bind(DeleteEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(GetEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(EmployeeListUseCaseFactory.class).toInstance(useCaseFactories);
		bind(PaymentFulfillUseCaseFactory.class).toInstance(useCaseFactories);
		bind(PayListUseCaseFactory.class).toInstance(useCaseFactories);
		bind(AddEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(AddTimeCardUseCaseFactory.class).toInstance(useCaseFactories);
		bind(UpdateTimeCardUseCaseFactory.class).toInstance(useCaseFactories);
		bind(ChangeToAbstractPaymentMethodUseCaseFactory.class).toInstance(useCaseFactories);
		bind(GetUnionMemberAffiliationUseCaseFactory.class).toInstance(useCaseFactories);
		bind(AddUnionMemberAffiliationUseCaseFactory.class).toInstance(useCaseFactories);
		bind(RemoveUnionMemberAffiliationUseCaseFactory.class).toInstance(useCaseFactories);
	}
	
	private void bindUIs() {
		bind(new TypeLiteral<AddEmployeeUI<?>>(){}).to(AddEmployeeUIImpl.class);
		bind(new TypeLiteral<ErrorDialogUI<?>>(){}).to(ErrorDialogUIImpl.class);
		bind(ConfirmDialogUI.class).to(ConfirmDialogUIImpl.class);
		bind(MainFrameUI.class).to(MainFrameUIImpl.class);
	}
	
	private void installAssistedFactories() {
		install(new FactoryModuleBuilder().implement(new TypeLiteral<AddTimeCardUI<? extends AddTimeCardView>>() {}, AddTimeCardUIImpl.class).build(AddTimeCardUIFactory.class));
		install(new FactoryModuleBuilder().implement(new TypeLiteral<AddUnionMemberUI<? extends AddUnionMemberView>>() {}, AddUnionMemberUIImpl.class).build(AddUnionMemberUIFactory.class));
		install(new FactoryModuleBuilder().build(AddTimeCardControllerFactory.class));
		install(new FactoryModuleBuilder().build(AddUnionMemberControllerFactory.class));
	}
	
}
