package hu.daniel.hari.testthis.data.inmemory.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt;

public class CommissionedPaymentClassificationImpl extends CommissionedPaymentClassification {

	private int biWeeklyBaseSalary;
	private double commissionRate;

	private Map<LocalDate, SalesReceipt> salesReceiptsByDate = new HashMap<>();

	
	public CommissionedPaymentClassificationImpl(int biWeeklyBaseSalary, double commissionRate) {
		this.biWeeklyBaseSalary = biWeeklyBaseSalary;
		this.commissionRate = commissionRate;
	}

	@Override
	public int getBiWeeklyBaseSalary() {
		return biWeeklyBaseSalary;
	}

	@Override
	public double getCommissionRate() {
		return commissionRate;
	}

	@Override
	public void addSalesReceipt(SalesReceipt salesReceipt) {
		salesReceiptsByDate.put(salesReceipt.getDate(), salesReceipt);
	}
	
	@Override
	public Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval) {
		return salesReceiptsByDate.entrySet().stream()
				.filter(entry -> dateInterval.isBetweenInclusive(entry.getKey()))
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
	}


}
