package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.PaymentClassification.PaymentClassificationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.UseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;

public class UseCaseFactoryImpl implements UseCaseFactory {

	private Injector injector;

	private List<Class<? extends UseCase<?>>> useCaseClassList = Arrays.asList(
					PaydayUseCase.class, 
					AddTimeCardUseCase.class, 
					AddSalariedEmployeeUseCase.class
					);
	private List<Class<? extends Object>> entityFactoryClasses = Arrays.asList(
					TimeCardFactory.class,
					EmployeeFactory.class,
					PaymentMethodFactory.class,
					AffiliationFactory.class,
					PaymentClassificationFactory.class,
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
