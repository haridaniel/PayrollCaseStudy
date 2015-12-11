package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.PayCheck;

public class PaydayUseCase extends TransactionalDatabaseUseCase {

	private LocalDate payDate;
	private Collection<PayCheck> payChecks = new ArrayList<>();

	public PaydayUseCase(PayrollDatabase payrollDatabase, LocalDate payDate) {
		super(payrollDatabase);
		this.payDate = payDate;
	}

	@Override
	protected void executeInTransaction() {
		Collection<Employee> employees = payrollDatabase.getAllEmployees();
		
		for (Employee employee : employees) {
			if(employee.isPayDate(payDate)) {
				payChecks.add(employee.createPayCheck(payDate));
			}
		}
	}
	
	//TODO: Useless. Paycheck has no employeeId
	public Collection<PayCheck> getPayChecks() {
		return payChecks;
	}
	
}
