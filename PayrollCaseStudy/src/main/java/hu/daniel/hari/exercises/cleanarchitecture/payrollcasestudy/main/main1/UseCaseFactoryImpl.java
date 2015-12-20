package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main1;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification.PaymentClassificationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.UseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.details.EntityFactory;

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
