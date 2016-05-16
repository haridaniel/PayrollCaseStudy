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
			.withBankTransferPortMock()
			.withLoadedTestData()
			.buildGuiAdminSwing()
			.run();
	}

	private static void case2() {
		Payroll.builder()
			.withDatabaseJPA(JPAPersistenceUnit.HSQL_DB)
			.withBankTransferPortMock()
			.withLoadedTestData()
			.buildGuiAdminSwing()
			.run();
	}
	

}