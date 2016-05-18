package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao.JPASalesReceiptDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.CommissionedJPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.JPAPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification.commissioned.SalesReceiptProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.SalesReceipt;

@AutoBindedProxy(CommissionedJPAPaymentType.class)
public class CommissionedPaymentTypeProxy extends CommissionedPaymentType implements PaymentTypeProxy {

	private CommissionedJPAPaymentType commissionedJPAPaymentType;

	@Inject private JPASalesReceiptDao salesReceiptDao;
	@Inject private ProxyFactory proxyFactory;
	
	@Inject
	public CommissionedPaymentTypeProxy(CommissionedJPAPaymentType commissionedJPAPaymentType) {
		this.commissionedJPAPaymentType = commissionedJPAPaymentType;
	}

	@Override
	public int getBiWeeklyBaseSalary() {
		return commissionedJPAPaymentType.getBiWeeklyBaseSalary();
	}

	@Override
	public double getCommissionRate() {
		return commissionedJPAPaymentType.getCommissionRate();
	}

	@Override
	public void addSalesReceipt(SalesReceipt salesReceipt) {
		commissionedJPAPaymentType.addJPASalesReceipt(((SalesReceiptProxy) salesReceipt).getJPAObject());
	}

	@Override
	public Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval) {
		return proxyAll(salesReceiptDao.findBy(commissionedJPAPaymentType.employeeId, dateInterval));
	}

	private Collection<SalesReceipt> proxyAll(Collection<JPASalesReceipt> jpaSalesReceipts) {
		return jpaSalesReceipts
				.stream()
				.map(jpaSalesReceipt -> proxy(jpaSalesReceipt))
				.collect(Collectors.toList());
	}

	private SalesReceipt proxy(JPASalesReceipt jpaSalesReceipt) {
		return proxyFactory.create(SalesReceiptProxy.class, jpaSalesReceipt);
	}

	@Override
	public JPAPaymentType getJPAObject() {
		return commissionedJPAPaymentType;
	}

}
