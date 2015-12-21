package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;

public interface DatabaseProvider {
	Database get();
}
