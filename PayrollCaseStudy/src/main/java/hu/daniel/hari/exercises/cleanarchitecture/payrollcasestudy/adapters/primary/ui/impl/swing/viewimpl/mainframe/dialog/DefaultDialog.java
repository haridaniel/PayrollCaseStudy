package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.dialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.ClosableView;

/**
 * Not close by itself, just send message to listener  
 * @author Dani
 *
 * @param <T>
 */
public class DefaultDialog<T extends CloseableViewListener> extends JDialog implements ClosableView<T> {

	private CloseableViewListener closeableViewListener;

	public DefaultDialog(
			JFrame parentFrame
			) {
		super(parentFrame);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeableViewListener.onCloseRequested();
			}
		});
	}

	@Override
	public void setListener(T listener) {
		this.closeableViewListener = listener;
	}

	@Override
	public void close() {
		dispose();
	}

}
