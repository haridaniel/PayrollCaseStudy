package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAAffiliation;
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

	@OneToOne(orphanRemoval=true, cascade= {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private JPAAffiliation jpaAffiliation;
	
	public JPAEmployee() {}
	
	public JPAPaymentClassification getJpaPaymentClassification() {
		return jpaPaymentClassification;
	}

	public JPAPaymentMethod getJpaPaymentMethod() {
		return jpaPaymentMethod;
	}

	public JPAPaymentSchedule getJpaPaymentSchedule() {
		return jpaPaymentSchedule;
	}

	public JPAAffiliation getJpaAffiliation() {
		return jpaAffiliation;
	}

	public void setJpaPaymentClassification(JPAPaymentClassification jpaPaymentClassification) {
		this.jpaPaymentClassification = jpaPaymentClassification;
	}

	public void setJpaPaymentMethod(JPAPaymentMethod jpaPaymentMethod) {
		this.jpaPaymentMethod = jpaPaymentMethod;
	}

	public void setJpaPaymentSchedule(JPAPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}
	
	public void setJpaAffiliation(JPAAffiliation jpaAffiliation) {
		this.jpaAffiliation = jpaAffiliation;
	}

}
