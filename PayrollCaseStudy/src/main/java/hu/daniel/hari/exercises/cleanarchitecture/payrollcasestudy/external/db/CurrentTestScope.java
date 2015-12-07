package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory.InMemoryPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

public class CurrentTestScope {
//	public static final PayrollDatabase DATABASE = new InMemoryPayrollDatabase();
	public static final PayrollDatabase DATABASE = new JPAPayrollDatabaseModule().getPayrollDatabase();
}
