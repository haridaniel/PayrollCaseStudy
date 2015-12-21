package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification;

import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public abstract class HourlyPaymentClassification extends PaymentClassification {
	private static final int DAILY_NORMAL_HOURS = 8;
	private static final double OVERTIME_WAGE_MULTIPLIER = 1.5d;

	public abstract void setHourlyWage(int hourlyWage);
	public abstract int getHourlyWage();

	public abstract void addTimeCard(TimeCard timeCard);
	public abstract Collection<TimeCard> getTimeCardsIn(DateInterval payInterval);

	@Override
	public int calculateAmount(DateInterval payInterval) {
		return getTimeCardsIn(payInterval).stream()
				.mapToInt(timeCard -> calculateAmount(timeCard))
				.sum();
	}

	private int calculateAmount(TimeCard timeCard) {
		SeparatedHours hours = SeparatedHours.of(timeCard.getWorkingHourQty());
		return calculateNormalTimeAmount(hours.normalHours) + calculateOvertimeAmount(hours.overTimeHours);
	}
	
	private int calculateNormalTimeAmount(int normalHours) {
		return normalHours * getHourlyWage();
	}

	private int calculateOvertimeAmount(int overTimeHours) {
		return (int)(overTimeHours * (getHourlyWage() * OVERTIME_WAGE_MULTIPLIER));
	}
	//TODO: Overengineered!
	private static class SeparatedHours {
		public final int normalHours;
		public final int overTimeHours;
		public SeparatedHours(int normalHours, int overTimeHours) {
			this.normalHours = normalHours;
			this.overTimeHours = overTimeHours;
		}
		public static SeparatedHours of(int workingHourQty) {
			if(workingHourQty <= DAILY_NORMAL_HOURS) {
				return new SeparatedHours(workingHourQty, 0);
			} else {
				return new SeparatedHours(DAILY_NORMAL_HOURS, workingHourQty - DAILY_NORMAL_HOURS);
			}
		}
	}

}
