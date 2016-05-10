package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.component.composite;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;

import com.google.common.base.Joiner;

public class ValidationErrorMessagesLabel extends JLabel {
	public ValidationErrorMessagesLabel() {
		super();
		setForeground(Color.RED);
	}

	public void setMessages(List<String> messages) {
		setText(toListAsHtml(messages));
	}
	
	private String toListAsHtml(List<String> strings) {
		return "<html>" + Joiner.on("<br/>").join(strings) + "</html>";
	}
}
