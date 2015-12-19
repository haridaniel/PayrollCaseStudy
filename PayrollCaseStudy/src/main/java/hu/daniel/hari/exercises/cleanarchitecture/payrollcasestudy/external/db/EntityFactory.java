package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge.ServiceChargeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification.PaymentClassificationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalesReceipt.SalesReceiptFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard.TimeCardFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;

public interface EntityFactory extends 
	EmployeeFactory, 
	PaymentClassificationFactory, 
	PaymentMethodFactory,
	PaymentScheduleFactory, 
	AffiliationFactory, 
	TimeCardFactory, 
	SalesReceiptFactory, 
	ServiceChargeFactory 

{}
