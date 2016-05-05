package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.UIImplConstants;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DialogView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;

/**
 * Don't closes by itself, just sends message to listener  
 * @author Dani
 *
 * @param <T>
 */
public class DefaultModalDialog<T extends CloseableViewListener> extends JDialog implements 
	DialogView<T> 
{

	private T listener;

	public DefaultModalDialog(JFrame parentFrame) {
		this(parentFrame, null);
	}
	public DefaultModalDialog(JFrame parentFrame, String title) {
		super(parentFrame);
		setTitle(buildTitle(title));
		setModal(true);
		setSize(450, 360);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseAction();
			}
		});
	}
	
	private String buildTitle(String title) {
		return UIImplConstants.APP_TITLE + (title == null? "" : " - " + title);
	}

	@Override
	public void setViewListener(T listener) {
		this.listener = listener;
	}
	
	protected T getViewListener() {
		return listener;
	}
	
	private void centerParent() {
		setLocationRelativeTo(getParent());
	}
	
	@Override
	public void close() {
		dispose();
	}

	protected void setFocus(JComponent component) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				component.requestFocusInWindow();
			}
		});
	}
	
	@Override
	public void showIt() {
		showDialog();
	}
	
	private void showDialog() {
		SwingUtilities.invokeLater(() -> {
			centerParent();
			setVisible(true);
		});
	}

}
