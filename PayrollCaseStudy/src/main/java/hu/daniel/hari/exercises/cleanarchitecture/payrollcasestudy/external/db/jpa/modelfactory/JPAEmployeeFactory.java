package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentmethod.JPAPaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentschedule.JPAPaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.paymentsize.JPAPaymentSizeFactory;

public class JPAEmployeeFactory {

	public static JPAEmployee create(Employee employee) {
		JPAEmployee jpaEmployee = new JPAEmployee();
		
		jpaEmployee.id = employee.getId();
		jpaEmployee.name = employee.getName();
		jpaEmployee.address = employee.getAddress();
		jpaEmployee.jpaPaymentMethod = employee.getPaymentMethod() == null ? null :
				JPAPaymentMethodFactory.create(employee.getPaymentMethod());
		jpaEmployee.jpaPaymentSize = employee.getPaymentClassification() == null ? null :
				JPAPaymentSizeFactory.create(jpaEmployee, employee.getPaymentClassification());
		jpaEmployee.jpaPaymentSchedule = employee.getPaymentSchedule() == null ? null :
				JPAPaymentScheduleFactory.create(employee.getPaymentSchedule());

		return jpaEmployee;
	}

}
