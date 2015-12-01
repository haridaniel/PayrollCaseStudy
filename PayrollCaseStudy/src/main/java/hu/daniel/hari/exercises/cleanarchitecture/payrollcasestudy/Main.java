package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory.InMemoryPayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.PayrollAppBoundary;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi.impl.PayrollAppBoundaryImpl;

public class Main {
	public static void main(String[] args) {
		
		
		
		PayrollDatabase payrollDatabase = new InMemoryPayrollDatabase();
		
		PayrollAppBoundary payrollAppBoundary = new PayrollAppBoundaryImpl(payrollDatabase);
		payrollAppBoundary.addSalariedEmployeeTransaction(1, "Bóób", "Addr1", 1553);
		
		
		
	}
}
