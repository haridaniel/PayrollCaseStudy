package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.PayrollAppBoundary;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.impl.PayrollAppBoundaryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory.InMemoryPayrollDatabase;

public class Main {
	public static void main(String[] args) {
		
		
		
		PayrollDatabase payrollDatabase = new InMemoryPayrollDatabase();
		
		PayrollAppBoundary payrollAppBoundary = new PayrollAppBoundaryImpl(payrollDatabase);
		payrollAppBoundary.addSalariedEmployeeTransaction(1, "Bóób", "Addr1", 1553);
		
		
		
	}
}
