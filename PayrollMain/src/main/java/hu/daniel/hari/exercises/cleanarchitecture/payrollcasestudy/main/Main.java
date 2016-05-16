package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnit;

public class Main {

	public static void main(String[] args) {
//		case1();
		case2();
	}

	private static void case1() {
		Payroll.builder()
			.withDatabaseInMemory()
			.withBankTransferPortFake()
			.withLoadedTestData()
			.buildGuiAdminSwing()
			.run();
	}

	private static void case2() {
		Payroll.builder()
			.withDatabaseJPA(JPAPersistenceUnit.HSQL_DB)
			.withBankTransferPortFake()
			.withLoadedTestData()
			.buildGuiAdminSwing()
			.run();
	}
	

}