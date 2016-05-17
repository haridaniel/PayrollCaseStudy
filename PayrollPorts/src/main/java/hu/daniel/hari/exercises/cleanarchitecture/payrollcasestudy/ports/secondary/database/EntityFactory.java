package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.ServiceCharge.ServiceChargeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.PaymentType.PaymentTypeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.SalesReceipt.SalesReceiptFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.TimeCard.TimeCardFactory;

public interface EntityFactory extends 
	EmployeeFactory, 
	PaymentTypeFactory, 
	PaymentMethodFactory,
	PaymentScheduleFactory, 
	AffiliationFactory, 
	TimeCardFactory, 
	SalesReceiptFactory, 
	ServiceChargeFactory 

{}
