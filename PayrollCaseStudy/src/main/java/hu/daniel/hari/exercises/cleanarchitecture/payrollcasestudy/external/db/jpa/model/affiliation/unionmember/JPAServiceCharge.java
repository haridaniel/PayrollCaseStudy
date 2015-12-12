package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAUnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JPAServiceCharge {
	
	@EmbeddedId
	public ID id = new ID();
	
	@Embeddable
	public static class ID implements Serializable {
		public Integer unionMemberId;
		public LocalDate date;

		public ID() {
		}
		public ID(Integer unionMemberId, LocalDate date) {
			this.unionMemberId = unionMemberId;
			this.date = date;
		}
	}
	
	public int amount;
	
	@ManyToOne  	
	@JoinColumn(name="unionMemberId", referencedColumnName="unionMemberId", insertable = false, updatable = false)
	public JPAUnionMemberAffiliation jpaUnionMemberAffiliation;

	public JPAServiceCharge() {}
	public JPAServiceCharge(LocalDate date, int amount) {
		this.id.date = date;
		this.amount = amount;
	}
	
	public void connect(JPAUnionMemberAffiliation jpaUnionMemberAffiliation) {
		this.jpaUnionMemberAffiliation = jpaUnionMemberAffiliation;
		this.id.unionMemberId = jpaUnionMemberAffiliation.getUnionMemberId();
	}
	
}
