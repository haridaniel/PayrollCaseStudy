package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabaseTest;

public class JPAPayrollDatabaseTest extends PayrollDatabaseTest {

	private static final PayrollDatabase PAYROLL_DATABASE = new JPAPayrollDatabaseModule().getPayrollDatabase();

	public JPAPayrollDatabaseTest() {
		super(PAYROLL_DATABASE);
	}
	
	@Override
	public void testAddTimeCard() throws Exception {
		super.testAddTimeCard();
	}

}
