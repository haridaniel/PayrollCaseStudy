package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database;

public interface Database {
	
	TransactionalRunner transactionalRunner();
	EntityFactory entityFactory();
	EmployeeGateway employeeGateway();

}