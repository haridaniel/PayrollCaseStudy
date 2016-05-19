package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events;

public class CalledNotImplementedFunctionEvent implements Event {
	public final String functionName;
	public CalledNotImplementedFunctionEvent(String functionName) {
		this.functionName = functionName;
	}
}
