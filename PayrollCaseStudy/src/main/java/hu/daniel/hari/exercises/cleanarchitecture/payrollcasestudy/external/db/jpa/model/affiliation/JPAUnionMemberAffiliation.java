package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember.JPAServiceCharge;

@Entity
public class JPAUnionMemberAffiliation extends JPAAffiliation implements Serializable {
	
	private int unionMemberId;
	private int weeklyDueAmount;

	@OneToMany(mappedBy="jpaUnionMemberAffiliation", orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	public Set<JPAServiceCharge> jpaServiceCharges = new HashSet<>();

	public JPAUnionMemberAffiliation() {
	}
	public JPAUnionMemberAffiliation(int unionMemberId, int weeklyDueAmount) {
		this.unionMemberId = unionMemberId;
		this.weeklyDueAmount = weeklyDueAmount;
	}
	
	public int getUnionMemberId() {
		return unionMemberId;
	}
	
	public int getWeeklyDueAmount() {
		return weeklyDueAmount;
	}

	public void addServiceCharge(JPAServiceCharge jpaServiceCharge) {
		jpaServiceCharge.connect(this);
		jpaServiceCharges.add(jpaServiceCharge);
	}

	
}
