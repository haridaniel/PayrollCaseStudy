package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

public enum JPAPersistenceUnit {
	HSQL_DB("hsql-db"),
	POSTGRES_LOCAL_DB("postgres-local-db"),
	ORACLE_LOCAL_DB("oracle-local-db")
	;
	
	public final String name;
	
	private JPAPersistenceUnit(String name) {
		this.name = name;
	}

}
