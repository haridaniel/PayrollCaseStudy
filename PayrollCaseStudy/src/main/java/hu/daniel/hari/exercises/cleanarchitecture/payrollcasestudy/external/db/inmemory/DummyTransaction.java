package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import javax.persistence.EntityTransaction;

public class DummyTransaction implements EntityTransaction {

	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRollbackOnly() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getRollbackOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}


}