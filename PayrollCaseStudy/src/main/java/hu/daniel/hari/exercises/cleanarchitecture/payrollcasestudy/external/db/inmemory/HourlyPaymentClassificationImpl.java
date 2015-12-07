package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HourlyPaymentClassificationImpl extends HourlyPaymentClassification {

	private int hourlyWage;

	private Map<LocalDate, TimeCard> timeCardsByDate = new HashMap<>();

	public HourlyPaymentClassificationImpl(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	@Override
	public int getHourlyWage() {
		return hourlyWage;
	}

	@Override
	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	@Override
	public void addTimeCard(TimeCard timeCard) {
		timeCardsByDate.put(timeCard.getDate(), timeCard);
	}

	@Override
	public Collection<TimeCard> getTimeCardsIn(DateInterval payInterval) {
		return timeCardsByDate.entrySet().stream()
				.filter(entry -> payInterval.isBetweenInclusive(entry.getKey()))
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
	}

}
