package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.dao.JPAEmployeeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;

public class JPAEntityGateway implements EmployeeGateway {
	@Inject private JPAEmployeeDao jPAEmployeeDao;
	@Inject private ProxyFactory proxyFactory;

	@Inject
	public JPAEntityGateway(JPAEmployeeDao jPAEmployeeDao) {
		this.jPAEmployeeDao = jPAEmployeeDao;
	}

	@Override
	public void addNew(Employee employee) {
		jPAEmployeeDao.persist(((EmployeeProxy) employee).getJPAObject());
	}

	@Override
	public Employee findById(int employeeId) {
		JPAEmployee jpaEmployee = jPAEmployeeDao.find(employeeId);
		assertNotNull(jpaEmployee);
		return proxy(jpaEmployee);
	}

	private void assertNotNull(JPAEmployee jpaEmployee) {
		if(jpaEmployee == null)
			throw new NoSuchEmployeeException();
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
	public int findByUnionMemberId(int unionMemberId) {
		try {
			return jPAEmployeeDao.getEmployeeIdByUnionMemberId(unionMemberId);
		} catch (NoResultException e) {
			throw new NoEmployeeWithSuchUnionMemberIdException();
		}
	}

}
