package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.affiliation;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.JPAEmployee;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(length=255)
public abstract class JPAAffiliation {
	
	@Id
	public Integer employeeId;
	
	@MapsId
	@OneToOne
	@JoinColumn(name="employeeId", referencedColumnName="id")
	public JPAEmployee jpaEmployee;

	public void connect(JPAEmployee jpaEmployee) {
		employeeId = jpaEmployee.id;
		this.jpaEmployee = jpaEmployee;
	}
	
	
}
