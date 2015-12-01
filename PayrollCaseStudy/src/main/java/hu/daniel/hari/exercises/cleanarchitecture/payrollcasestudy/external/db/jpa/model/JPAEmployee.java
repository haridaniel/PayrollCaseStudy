package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;

@Entity
public class JPAEmployee {
	
    @Id
	public int id;
    public String name;
	public String address;

}
