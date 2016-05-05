package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.hourly;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.HourlyJPAPaymentType;

@Entity
public class JPATimeCard {
	
	@EmbeddedId
	public ID id = new ID();
	
	@Embeddable
	public static class ID implements Serializable {
		public Integer employeeId;
		
		@Column(name="\"date\"")
		public LocalDate date;

		public ID() {
		}
		public ID(Integer employeeId, LocalDate date) {
			this.employeeId = employeeId;
			this.date = date;
		}
	}
	
	public int workingHourQty;
	
	@ManyToOne  	
	@JoinColumn(name="employeeId", referencedColumnName="employeeId", insertable = false, updatable = false)
	public HourlyJPAPaymentType hourlyJPAPaymentType;

	public JPATimeCard() {}
	public JPATimeCard(LocalDate date, int workingHoursQty) {
		id.date = date;
		this.workingHourQty = workingHoursQty;
	}
	
	public void connect(HourlyJPAPaymentType hourlyJPAPaymentType) {
		this.hourlyJPAPaymentType = hourlyJPAPaymentType;
		this.id.employeeId = hourlyJPAPaymentType.employeeId;
	}
	
}
