package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public interface DatabaseProvider {
	Database get();
}
