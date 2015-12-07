package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabaseTest;

public class InMemoryPayrollDatabaseTest extends PayrollDatabaseTest {

	public InMemoryPayrollDatabaseTest() {
		super(new InMemoryPayrollDatabase());
	}

	
}
