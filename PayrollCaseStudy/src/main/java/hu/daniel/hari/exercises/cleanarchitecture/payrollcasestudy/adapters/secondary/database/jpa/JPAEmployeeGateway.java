package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao.JPAEmployeeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;

public class JPAEmployeeGateway implements EmployeeGateway {
	
	@Inject private JPAEmployeeDao jPAEmployeeDao;
	@Inject private ProxyFactory proxyFactory;

	@Inject
	public JPAEmployeeGateway(JPAEmployeeDao jPAEmployeeDao) {
		this.jPAEmployeeDao = jPAEmployeeDao;
	}

	@Override
	public void addNew(Employee employee) {
		jPAEmployeeDao.persist(((EmployeeProxy) employee).getJPAObject());
	}

	@Override
	public boolean isExists(int employeeId) {
		return jPAEmployeeDao.isExists(employeeId);
	}

	@Override
	public Employee findById(int employeeId) {
		return proxy(assertNotNull(jPAEmployeeDao.find(employeeId)));
	}

	@Override
	public Collection<Employee> findAll() {
		return proxyAll(jPAEmployeeDao.findAll());
	}

	@Override
	public void deleteById(int employeeId) {
		jPAEmployeeDao.delete(employeeId);
	}

	@Override
	public void deleteAll() {
		jPAEmployeeDao.deleteAll();
	}

	private JPAEmployee assertNotNull(JPAEmployee jpaEmployee) {
		if(jpaEmployee == null)
			throw new NoSuchEmployeeException();
		return jpaEmployee;
	}

	private Collection<Employee> proxyAll(List<JPAEmployee> findAll) {
		return findAll
				.stream()
				.map(jPAEmployee -> proxy(jPAEmployee))
				.collect(Collectors.toList());
	}

	private Employee proxy(JPAEmployee jpaEmployee) {
		return proxyFactory.create(EmployeeProxy.class, jpaEmployee);
	}

	@Override
	public boolean isEmployeeExistsByUnionMemberId(int unionMemberId) {
		return jPAEmployeeDao.isEmployeeExistsByUnionMemberId(unionMemberId);
	}
	
	@Override
	public int findEmployeeIdByUnionMemberId(int unionMemberId) {
		try {
			return jPAEmployeeDao.getEmployeeIdByUnionMemberId(unionMemberId);
		} catch (NoResultException e) {
			throw new NoSuchEmployeeException();
		}
	}

}
