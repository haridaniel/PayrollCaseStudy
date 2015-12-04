package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentsize;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class HourlyJPAPaymentClassification extends JPAPaymentClassification {

	private int hourlyWage;

	@OneToMany(mappedBy="hourlyJPAPaymentClassification", orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	public Set<JPATimeCard> jpaTimeCards = new HashSet<>();
	
	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public void addJPATimeCard(JPATimeCard timeCard) {
		jpaTimeCards.add(timeCard);
	}

	public TimeCard getTimeCard(LocalDate date) {
		//findTimeCard or something
		throw new NotImplementedException();
	}
	
}
