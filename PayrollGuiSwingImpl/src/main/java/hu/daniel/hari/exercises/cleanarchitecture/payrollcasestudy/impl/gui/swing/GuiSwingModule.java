package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.util.Providers;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.common.SmartDateFormatter.SmartDateFormatterFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardUI.AddTimeCardUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberController.AddUnionMemberControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberUI.AddUnionMemberUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addunionmemberaffiliation.AddUnionMemberView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.common.error.ErrorDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.MainFrameUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table.EmployeeListPresenter.EmployeeListPresenterFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.util.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.util.UncaugthExceptionHandler;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee.AddEmployeeUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addtimecard.AddTimeCardUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addunionmemberaffiliation.AddUnionMemberUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.common.ConfirmDialogUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.common.ErrorDialogUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.UseCaseFactories;
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

class GuiSwingModule extends AbstractModule {
	private Provider<UseCaseFactories> useCaseFactories;

	public GuiSwingModule(UseCaseFactories useCaseFactories) {
		this.useCaseFactories = Providers.of(useCaseFactories);
	}

	@Override
	protected void configure() {
		bindUseCaseFactories();
		bindUIs();
		bindEagerSingletons();
		installAssistedFactories();
	}

	private void bindUseCaseFactories() {
		bind(DeleteEmployeeUseCaseFactory.class).toProvider(useCaseFactories);
		bind(GetEmployeeUseCaseFactory.class).toProvider(useCaseFactories);
		bind(EmployeeListUseCaseFactory.class).toProvider(useCaseFactories);
		bind(PaymentFulfillUseCaseFactory.class).toProvider(useCaseFactories);
		bind(PayListUseCaseFactory.class).toProvider(useCaseFactories);
		bind(AddEmployeeUseCaseFactory.class).toProvider(useCaseFactories);
		bind(AddTimeCardUseCaseFactory.class).toProvider(useCaseFactories);
		bind(UpdateTimeCardUseCaseFactory.class).toProvider(useCaseFactories);
		bind(ChangeToAbstractPaymentMethodUseCaseFactory.class).toProvider(useCaseFactories);
		bind(GetUnionMemberAffiliationUseCaseFactory.class).toProvider(useCaseFactories);
		bind(AddUnionMemberAffiliationUseCaseFactory.class).toProvider(useCaseFactories);
		bind(RemoveUnionMemberAffiliationUseCaseFactory.class).toProvider(useCaseFactories);
	}

	private void bindUIs() {
		bind(new TypeLiteral<AddEmployeeUI<?>>(){}).to(AddEmployeeUIImpl.class);
		bind(new TypeLiteral<ErrorDialogUI<?>>(){}).to(ErrorDialogUIImpl.class);
		bind(ConfirmDialogUI.class).to(ConfirmDialogUIImpl.class);
		bind(MainFrameUI.class).to(MainFrameUIImpl.class);
	}

	private void bindEagerSingletons() {
		bind(EventBus.class).to(EventQueueAsyncEventBus.class).asEagerSingleton();
		bind(UncaugthExceptionHandler.class).asEagerSingleton();
	}

	private void installAssistedFactories() {
		install(new FactoryModuleBuilder().implement(new TypeLiteral<AddTimeCardUI<? extends AddTimeCardView>>() {}, AddTimeCardUIImpl.class).build(AddTimeCardUIFactory.class));
		install(new FactoryModuleBuilder().implement(new TypeLiteral<AddUnionMemberUI<? extends AddUnionMemberView>>() {}, AddUnionMemberUIImpl.class).build(AddUnionMemberUIFactory.class));
		install(new FactoryModuleBuilder().build(AddTimeCardControllerFactory.class));
		install(new FactoryModuleBuilder().build(AddUnionMemberControllerFactory.class));
		install(new FactoryModuleBuilder().build(EmployeeListPresenterFactory.class));
		install(new FactoryModuleBuilder().build(SmartDateFormatterFactory.class));
	}
	
}
