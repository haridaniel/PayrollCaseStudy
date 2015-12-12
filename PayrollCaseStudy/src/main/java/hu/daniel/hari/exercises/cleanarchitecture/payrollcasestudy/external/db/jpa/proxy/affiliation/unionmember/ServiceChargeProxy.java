package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.unionmember;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember.JPAServiceCharge;

public class ServiceChargeProxy implements ServiceCharge {

	private JPAServiceCharge jpaServiceCharge;

	public ServiceChargeProxy(JPAServiceCharge jpaServiceCharge) {
		this.jpaServiceCharge = jpaServiceCharge;
	}
	
	public JPAServiceCharge getJpaServiceCharge() {
		return jpaServiceCharge;
	}

	@Override
	public int getAmount() {
		return jpaServiceCharge.amount;
	}

	@Override
	public LocalDate getDate() {
		return jpaServiceCharge.id.date;
	}

	@Override
	public void setAmount(int amount) {
		jpaServiceCharge.amount = amount;
	}

	@Override
	public void setDate(LocalDate date) {
		jpaServiceCharge.id.date = date;
	}

}
