package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.TimeCard;

public class HourlyPaymentTypeImpl extends HourlyPaymentType {

	private int hourlyWage;

	private Map<LocalDate, TimeCard> timeCardsByDate = new HashMap<>();

	public HourlyPaymentTypeImpl(int hourlyWage) {
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
	public Collection<TimeCard> getTimeCardsIn(DateInterval dateInterval) {
		return timeCardsByDate.keySet().stream()
				.filter(date -> dateInterval.isBetweenInclusive(date))
				.map(date -> timeCardsByDate.get(date))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<TimeCard> getTimeCard(LocalDate date) {
		return getTimeCardsIn(DateInterval.ofSingleDate(date)).stream()
				.findFirst();
	}

}
