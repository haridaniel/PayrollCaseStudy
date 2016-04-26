package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite.StatusBarTextPane;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.statusbar.StatusBarView.StatusBarViewModel.MessageType;

public class StatusBarPanel extends JPanel implements StatusBarView {
	private final Color INFO_COLOR = Color.BLACK;
	private final Color CONFIRM_COLOR = Color.decode("#00AA00");//GREEN
	private final Color ERROR_COLOR = Color.red;
	
	private StatusBarTextPane statusBarTextPane;

	public StatusBarPanel() {
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());
		statusBarTextPane = new StatusBarTextPane();
		add(statusBarTextPane);
	}

	@Override
	public void setModel(StatusBarViewModel model) {
		setMessage(model.message, toColor(model.messageType));
	}

	private void setMessage(String message, Color backgroundColor) {
		statusBarTextPane.setMessage(message, backgroundColor);
	}

	private Color toColor(MessageType messageType) {
		switch (messageType) {
		case INFO:
			return INFO_COLOR;
		case CONFIRM:
			return CONFIRM_COLOR;
		case ERROR:
			return ERROR_COLOR;
		default:
			throw new IllegalArgumentException();
		}
	}
}
