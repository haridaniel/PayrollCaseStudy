package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.JPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentmethod.JPAPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentschedule.JPAPaymentSchedule;

@Entity
public class JPAEmployee {
	
	@Id
	public int id;
    public String name;
	public String address;
	
	@OneToOne(orphanRemoval=true, cascade= {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private JPAPaymentType jpaPaymentType;
	
	@OneToOne(orphanRemoval=true, cascade= {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private JPAPaymentSchedule jpaPaymentSchedule;
	
	@OneToOne(orphanRemoval=true, cascade= {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private JPAPaymentMethod jpaPaymentMethod;

	@OneToOne(orphanRemoval=true, cascade= {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private JPAAffiliation jpaAffiliation;
	
	public JPAEmployee() {}
	
	public JPAPaymentType getJpaPaymentType() {
		return jpaPaymentType;
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

	public void setJpaPaymentType(JPAPaymentType jpaPaymentType) {
		this.jpaPaymentType = jpaPaymentType;
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
