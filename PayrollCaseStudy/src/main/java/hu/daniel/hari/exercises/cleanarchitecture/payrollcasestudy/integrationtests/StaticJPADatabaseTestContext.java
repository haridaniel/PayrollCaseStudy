package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.JPAPayrollDatabaseModule;

public class StaticJPADatabaseTestContext {
	
	private static StaticJPADatabaseTestContext INSTANCE;

	public synchronized static StaticJPADatabaseTestContext get() {
		if(INSTANCE == null)
			INSTANCE = new StaticJPADatabaseTestContext();
		return INSTANCE;
	}
	
	private PayrollDatabase payrollDatabase;

	public StaticJPADatabaseTestContext() {
		payrollDatabase = new JPAPayrollDatabaseModule().getPayrollDatabase();
	}
	
	public PayrollDatabase getPayrollDatabase() {
		return payrollDatabase;
	}
	
	
	
	
}
