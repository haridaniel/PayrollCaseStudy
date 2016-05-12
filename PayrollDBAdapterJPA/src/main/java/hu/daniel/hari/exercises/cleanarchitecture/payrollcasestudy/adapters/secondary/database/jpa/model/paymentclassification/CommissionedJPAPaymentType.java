package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;

@Entity
public class CommissionedJPAPaymentType extends JPAPaymentType {

	private int biWeeklyBaseSalary;
	private double commissionRate;

	@OneToMany(mappedBy = "commissionedJPAPaymentType", orphanRemoval = true, cascade = { CascadeType.ALL })
	@PrimaryKeyJoinColumn
	private Set<JPASalesReceipt> jpaSalesReceipts = new HashSet<>();

	public CommissionedJPAPaymentType() {}
	public CommissionedJPAPaymentType(int biWeeklyBaseSalary, double commissionRate) {
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}

	public int getBiWeeklyBaseSalary() {
		return biWeeklyBaseSalary;
	}
	
	public void setBiWeeklyBaseSalary(int biWeeklyBaseSalary) {
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public void addJPASalesReceipt(JPASalesReceipt jpaSalesReceipt) {
		jpaSalesReceipt.connect(this);
		jpaSalesReceipts.add(jpaSalesReceipt);
	}


}
