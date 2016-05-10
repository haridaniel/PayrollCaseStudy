package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.dialog.common;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.CloseableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI.ConfirmDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.component.composite.OkCancelButtonBar;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.component.composite.OkCancelButtonBar.OkCancelButtonBarListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.dialog.DefaultModalDialog;

/** This has no controller */
public class ConfirmDialog extends DefaultModalDialog<CloseableViewListener> implements CloseableViewListener, OkCancelButtonBarListener{
	private JLabel lbMessage;
	
	private ConfirmDialogListener confirmDialogListener;
	private String okLabelString;
	private String cancelLabelString;

	public ConfirmDialog(
			JFrame parentFrame,
			String message,
			ConfirmDialogListener confirmDialogListener,
			String okLabelString, String cancelLabelString
			) {
		super(parentFrame);
		this.confirmDialogListener = confirmDialogListener;
		this.okLabelString = okLabelString;
		this.cancelLabelString = cancelLabelString;
		setViewListener(this);
		initUI();
		setMessage(message);
	}
	
	public ConfirmDialog(JFrame parentFrame, String message, ConfirmDialogListener confirmDialogListener) {
		this(parentFrame, message, confirmDialogListener, "Confirm", "Cancel");
	}

	private void setMessage(String message) {
		lbMessage.setText(toCenteredHtml(message));
	}
	
	private String toCenteredHtml(String message) {
		return String.format("<html><div style='text-align: center;'>%s </html>", message);
	}
	
	@Override
	public void onOk() {
		confirmDialogListener.onOk();
		close();
	}
	
	@Override
	public void onCancel() {
		close();
	}

	@Override
	public void onCloseAction() {
		close();
	}
	
	private void initUI() {
			setLocationRelativeTo(getParent());
			setModal(true);
	
			setSize(300, 200);
			getContentPane().setLayout(new BorderLayout());
	
			lbMessage = new JLabel();
			lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lbMessage, BorderLayout.CENTER);
			
			OkCancelButtonBar okCancelButtonBar = new OkCancelButtonBar(this, okLabelString, cancelLabelString);
			setFocus(okCancelButtonBar.okButton);
			getContentPane().add(okCancelButtonBar, BorderLayout.SOUTH);
			
		}

}
