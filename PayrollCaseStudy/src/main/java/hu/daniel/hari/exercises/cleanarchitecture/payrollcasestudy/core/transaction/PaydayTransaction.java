package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.PayCheck;

public class PaydayTransaction extends PayrollDatabaseTransaction {

	private LocalDate date;
	private Collection<PayCheck> payChecks = new ArrayList<>();

	public PaydayTransaction(PayrollDatabase payrollDatabase, LocalDate date) {
		super(payrollDatabase);
		this.date = date;
	}

	@Override
	public void execute() {
		
		Collection<Employee> employees = payrollDatabase.getAllEmployees();
		
		for (Employee employee : employees) {
			int payAmount = employee.calculatePayAmount(date);
			System.out.println(payAmount);
			
			PayCheck payCheck = new PayCheck(employee.getId(), payAmount);
			payChecks.add(payCheck);
		}
		
	}

	public Collection<PayCheck> getPayChecks() {
		return payChecks;
	}
	
}
