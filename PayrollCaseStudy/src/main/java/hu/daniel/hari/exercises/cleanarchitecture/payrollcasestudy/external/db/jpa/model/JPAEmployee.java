package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize.JPAPaymentSize;

@Entity
public class JPAEmployee {
	
	@Id
	public int id;
    public String name;
	public String address;
	
	@OneToOne(orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	public JPAPaymentSize jpaPaymentSize;
	
	@Enumerated(EnumType.STRING)
	public JPAPaymentSchedule jpaPaymentSchedule;
	
	@Enumerated(EnumType.STRING)
	public JPAPaymentMethod jpaPaymentMethod;

	public enum JPAPaymentSchedule {
		WEEKLY,
		MONTHLY
	}
	
	public enum JPAPaymentMethod {
		HOLD
	}
	
}
