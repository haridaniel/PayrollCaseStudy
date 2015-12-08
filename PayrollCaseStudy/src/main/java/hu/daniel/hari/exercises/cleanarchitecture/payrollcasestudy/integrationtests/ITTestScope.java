package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.InMemoryPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

public class ITTestScope {
//	public static final PayrollDatabase DATABASE = new InMemoryPayrollDatabase();
	public static final PayrollDatabase DATABASE = new JPAPayrollDatabaseModule().getPayrollDatabase();
}
