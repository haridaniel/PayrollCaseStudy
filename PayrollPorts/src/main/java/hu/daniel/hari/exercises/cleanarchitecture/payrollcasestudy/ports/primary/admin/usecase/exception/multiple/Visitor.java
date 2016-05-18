package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.multiple;

public interface Visitor<V extends Visitor<V, A>, A extends Visitable<V, A>> {
}
