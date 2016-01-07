package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing.viewimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.dialog.uncaugthexception.UncaugthExceptionView;

public class UncaugthExceptionDialog extends JDialog implements UncaugthExceptionView {
	private UncaugthExceptionViewListener listener;
	private JTextPane textPane;

	public UncaugthExceptionDialog(JFrame parentFrame) {
		super(parentFrame);
		initUI();
		centerParent();
	}
	private void centerParent() {
		setLocationRelativeTo(getParent());
	}
	@Override
	public void setListener(UncaugthExceptionViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setModel(UncaugthExceptionViewModel model) {
		textPane.setText(model.stackTraceString);
		textPane.setCaretPosition(0);
	}

	@Override
	public void close() {
		dispose();
	}

	private void initUI() {
		setLocationRelativeTo(getParent());
		setModal(true);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());

		JLabel lblNewLabel = new JLabel("Uncaugth exception:");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(new BorderLayout(0, 0));

				textPane = new JTextPane();
				textPane.setEditable(false);
				panel.add(textPane);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listener.onClose();
					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}

	}


}
