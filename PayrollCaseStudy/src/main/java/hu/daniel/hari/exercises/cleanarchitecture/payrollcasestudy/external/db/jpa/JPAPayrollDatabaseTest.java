package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import static org.junit.Assert.*;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabaseTest;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JPAPayrollDatabaseTest extends PayrollDatabaseTest {

	private static PayrollDatabase payrollDatabase = new JPAPayrollDatabaseModule().getPayrollDatabase();

	public JPAPayrollDatabaseTest() {
		super(payrollDatabase);
	}
	
	@Override
	public void testAddTimeCard() throws Exception {
		super.testAddTimeCard();
	}
	
}
