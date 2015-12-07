package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.JPAPaymentClassification;

@Entity
public class JPAEmployee {
	
	@Id
	public int id;
    public String name;
	public String address;
	
	@OneToOne(orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	private JPAPaymentClassification jpaPaymentClassification;
	
	@Enumerated(EnumType.STRING)
	private JPAPaymentSchedule jpaPaymentSchedule;
	
	@Enumerated(EnumType.STRING)
	private JPAPaymentMethod jpaPaymentMethod;

	public enum JPAPaymentSchedule {
		WEEKLY,
		MONTHLY
	}
	
	public enum JPAPaymentMethod {
		HOLD
	}

	public JPAPaymentClassification getJpaPaymentClassification() {
		return jpaPaymentClassification;
	}

	public void setJpaPaymentClassification(JPAPaymentClassification jpaPaymentClassification) {
		this.jpaPaymentClassification = jpaPaymentClassification;
		jpaPaymentClassification.connect(this);
	}

	public JPAPaymentMethod getJpaPaymentMethod() {
		return jpaPaymentMethod;
	}

	public void setJpaPaymentMethod(JPAPaymentMethod jpaPaymentMethod) {
		this.jpaPaymentMethod = jpaPaymentMethod;
	}

	public JPAPaymentSchedule getJpaPaymentSchedule() {
		return jpaPaymentSchedule;
	}

	public void setJpaPaymentSchedule(JPAPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}

}
