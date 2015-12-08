package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.InMemoryPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

@RunWith(Parameterized.class)
public abstract class AbstractDatabaseITTest {
	
	@Parameterized.Parameters(name="{0}")
	public static Collection<PayrollDatabase[]> getDatabasesToTestOn() {
		return Arrays.asList(new PayrollDatabase[][] {
			
			{new InMemoryPayrollDatabase()},
//			{new JPAPayrollDatabaseModule().getPayrollDatabase()}
			
		});
	}
	
	protected PayrollDatabase database;

	public AbstractDatabaseITTest(PayrollDatabase payrollDatabase) {
		this.database = payrollDatabase;
	}

}