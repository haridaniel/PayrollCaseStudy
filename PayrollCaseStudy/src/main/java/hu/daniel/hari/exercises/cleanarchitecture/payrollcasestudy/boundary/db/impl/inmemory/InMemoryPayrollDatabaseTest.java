package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabaseTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.InMemoryEntityFactory;

public class InMemoryPayrollDatabaseTest extends PayrollDatabaseTest {

	public InMemoryPayrollDatabaseTest() {
		super(new InMemoryPayrollDatabase());
	}

	
}
