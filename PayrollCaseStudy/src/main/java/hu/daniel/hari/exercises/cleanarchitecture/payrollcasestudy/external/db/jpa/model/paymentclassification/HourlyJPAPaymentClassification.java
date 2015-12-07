package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;

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
	
	@Deprecated
	public HourlyJPAPaymentClassification() {
	}
	public HourlyJPAPaymentClassification(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public void addJPATimeCard(JPATimeCard jpaTimeCard) {
		connect(jpaTimeCard);
		jpaTimeCards.add(jpaTimeCard);
	}

	private void connect(JPATimeCard jpaTimeCard) {
		jpaTimeCard.hourlyJPAPaymentClassification = this;
		jpaTimeCard.id.employeeId = this.employeeId;
	}
	
	public TimeCard getTimeCard(LocalDate date) {
		//findTimeCard or something
		throw new NotImplementedException();
	}
	
}
