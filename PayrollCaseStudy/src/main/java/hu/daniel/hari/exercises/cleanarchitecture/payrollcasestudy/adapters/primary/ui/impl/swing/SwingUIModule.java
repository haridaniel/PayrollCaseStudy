package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing;

import javax.swing.JFrame;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.error.UncaugthExceptionHandler;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.MainFrameUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.util.eventbus.EventQueueAsyncEventBus;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase.PayListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.SendPayUseCase.SendPayUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;

public class SwingUIModule extends AbstractModule {
	private UseCaseFactories useCaseFactories;
	private EventBus eventBus;

	public SwingUIModule(UseCaseFactories useCaseFactories) {
		this.useCaseFactories = useCaseFactories;
		eventBus = createEventBus();
	}
	
	private EventBus createEventBus() {
		return new EventQueueAsyncEventBus();
	}

	@Override
	protected void configure() {
		bind(UseCaseFactories.class).toInstance(useCaseFactories);
		bind(EventBus.class).toInstance(eventBus);
		bind(UncaugthExceptionHandler.class).asEagerSingleton();
		bindUseCaseFactories();
	}

	private void bindUseCaseFactories() {
		bind(DeleteEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(GetEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
		bind(ListEmployeesUseCaseFactory.class).toInstance(useCaseFactories);
		bind(SendPayUseCaseFactory.class).toInstance(useCaseFactories);
		bind(PayListUseCaseFactory.class).toInstance(useCaseFactories);
		bind(AddEmployeeUseCaseFactory.class).toInstance(useCaseFactories);
	}
	
	@Provides 
	JFrame getRootFrame(Injector injector) {
		return injector.getInstance(MainFrameUI.class).view;
	}

}
