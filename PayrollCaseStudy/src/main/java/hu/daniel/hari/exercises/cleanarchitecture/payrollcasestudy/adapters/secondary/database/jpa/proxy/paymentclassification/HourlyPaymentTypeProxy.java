package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao.JPATimeCardDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.HourlyJPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.JPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.SalariedJPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification.hourly.TimeCardProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.TimeCard;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

@AutoBindedProxy(HourlyJPAPaymentType.class)
public class HourlyPaymentTypeProxy extends HourlyPaymentType implements PaymentTypeProxy {

	private HourlyJPAPaymentType hourlyJPAPaymentType;
	
	@Inject	private JPATimeCardDao timeCardDao;
	@Inject private ProxyFactory proxyFactory;

	@Inject
	public HourlyPaymentTypeProxy(HourlyJPAPaymentType hourlyJPAPaymentType) {
		this.hourlyJPAPaymentType = hourlyJPAPaymentType;
	}
	
	@Override
	public int getHourlyWage() {
		return hourlyJPAPaymentType.getHourlyWage();
	}
	
	@Override
	public void setHourlyWage(int hourlyWage) {
		hourlyJPAPaymentType.setHourlyWage(hourlyWage);
	}
	
	@Override
	public void addTimeCard(TimeCard timeCard) {
		hourlyJPAPaymentType.addJPATimeCard(((TimeCardProxy) timeCard).getJPAObject());
	}

	@Override
	public Collection<TimeCard> getTimeCardsIn(DateInterval dateInterval) {
		return proxyAll(timeCardDao.findJPATimeCardsIn(dateInterval));
	}

	private List<TimeCard> proxyAll(Collection<JPATimeCard> jpaTimeCards) {
		return jpaTimeCards
				.stream()
				.map(jpaTimeCard -> proxy(jpaTimeCard))
				.collect(Collectors.toList());
	}

	private TimeCardProxy proxy(JPATimeCard jpaTimeCard) {
		return proxyFactory.create(TimeCardProxy.class, jpaTimeCard);
	}

	@Override
	public JPAPaymentType getJPAObject() {
		return hourlyJPAPaymentType;
	}
	
}
