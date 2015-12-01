package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabaseTest;

public class InMemoryPayrollDatabaseTest extends PayrollDatabaseTest {

	public InMemoryPayrollDatabaseTest() {
		super(new InMemoryPayrollDatabase());
	}

	
}
