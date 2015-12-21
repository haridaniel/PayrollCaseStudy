package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.interfaceadapters.database;

import javax.inject.Provider;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;

public interface DatabaseProvider extends Provider<Database>{
}
