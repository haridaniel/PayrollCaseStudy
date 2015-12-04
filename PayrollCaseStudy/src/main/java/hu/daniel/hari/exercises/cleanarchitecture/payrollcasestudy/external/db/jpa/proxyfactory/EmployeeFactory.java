package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxyfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy;

public class EmployeeFactory {
	public static Employee create() {
		return new EmployeeProxy(new JPAEmployee());
	}
}
