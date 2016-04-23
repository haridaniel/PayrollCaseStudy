package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OkCancelButtonBar extends JPanel {
	public JButton okButton;
	
	private OkCancelButtonBarListener listener;
	private String okLabelString;
	private String cancelLabelString;

	public OkCancelButtonBar(OkCancelButtonBarListener listener) {
		this(listener, "OK", "CANCEL");
	}
	public OkCancelButtonBar(OkCancelButtonBarListener listener, String okLabelString, String cancelLabelString) {
		this.listener = listener;
		this.okLabelString = okLabelString;
		this.cancelLabelString = cancelLabelString;
		initUI();
	}
	
	
	private void initUI() {
		JPanel buttonPane = this;
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		{
			okButton = new JButton(okLabelString);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listener.onOk();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
		}
		{
			JButton cancelButton = new JButton(cancelLabelString);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listener.onCancel();
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}		
	}


	public interface OkCancelButtonBarListener {
		void onOk();
		void onCancel();
	}
	
}
