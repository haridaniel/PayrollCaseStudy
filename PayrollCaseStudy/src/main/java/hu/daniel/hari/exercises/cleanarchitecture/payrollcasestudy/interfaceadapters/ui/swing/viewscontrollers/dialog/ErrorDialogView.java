package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.util.SpringUtilities;

import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class ErrorDialogView extends JDialog {
	private ErrorDialogListener listener;
	private JTextPane textPane;
	
	public ErrorDialogView() {
		super();
		initUI();
	}
	
	public void setListener(ErrorDialogListener listener) {
		this.listener = listener;
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
	
	public interface ErrorDialogListener {
		void onClose();
	}

	public void setModel(ErrorDialogViewModel model) {
		textPane.setText(model.stackTraceString);
		textPane.setCaretPosition(0);
	}
	
	
}
