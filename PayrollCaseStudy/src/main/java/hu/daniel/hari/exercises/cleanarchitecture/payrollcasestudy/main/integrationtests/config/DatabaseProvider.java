package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config;

import javax.inject.Provider;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;

public interface DatabaseProvider extends Provider<Database>{
}
