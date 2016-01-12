package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType.PaymentTypeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.UseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.generate.GeneratePayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class UseCaseFactoryImpl implements UseCaseFactory {

	private Injector injector;

	private List<Class<? extends UseCase<?>>> useCaseClassList = Arrays.asList(
					GeneratePayUseCase.class, 
					AddTimeCardUseCase.class, 
					AddSalariedEmployeeUseCase.class
					);
	private List<Class<? extends Object>> entityFactoryClasses = Arrays.asList(
					TimeCardFactory.class,
					EmployeeFactory.class,
					PaymentMethodFactory.class,
					AffiliationFactory.class,
					PaymentTypeFactory.class,
					PaymentScheduleFactory.class
					);

	
	public UseCaseFactoryImpl(Database database) {
		this.injector = Guice.createInjector(Stage.DEVELOPMENT, 
				new UseCaseFactoryModule(database));		
	}
	
	@Override
	public <T extends UseCase<?>> T create(Class<T> useCaseType) {
		return injector.getInstance(useCaseType);
	}

	
	public class UseCaseFactoryModule extends AbstractModule {

		private Database database;

		public UseCaseFactoryModule(Database database) {
			this.database = database;
		}

		@Override
		protected void configure() {
			bindUseCasesToTheirFirstConstructor(useCaseClassList);
			bindEntityFactoryClassesToEntityFactory(entityFactoryClasses);
			bind(TransactionalRunner.class).toInstance(database.transactionalRunner());
			bind(EmployeeGateway.class).toInstance(database.employeeGateway());
		}

		private void bindEntityFactoryClassesToEntityFactory(List<Class<? extends Object>> entityFactoryClasses) {
			for (Class<? extends Object> entityFactoryClass : entityFactoryClasses) {
				bind((Class<EntityFactory>) entityFactoryClass).toInstance(database.entityFactory());
			}
		}

		private void bindUseCasesToTheirFirstConstructor(List<Class<? extends UseCase<?>>> useCaseClassList) {
			for (Class<? extends UseCase<?>> useCaseClass : useCaseClassList) {
				bindToFirstConstructor(useCaseClass);
			}
		}

		private <T> void bindToFirstConstructor(Class<T> clazz) {
			bind(clazz).toConstructor((Constructor<T>) clazz.getConstructors()[0]);
		}
		
	}
}
