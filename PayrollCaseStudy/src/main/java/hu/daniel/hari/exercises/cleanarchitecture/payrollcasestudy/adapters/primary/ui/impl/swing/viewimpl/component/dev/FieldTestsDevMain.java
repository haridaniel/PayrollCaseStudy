package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.dev;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FieldTestsDevMain extends JFrame {

	private JPanel contentPane;
	private DateField dateField;
	private JFormattedTextField formattedTextField;

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private JLabel lblFormattedtextfield;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> { 
			FieldTestsDevMain frame = new FieldTestsDevMain();
			frame.init();
			frame.setVisible(true);
		});
	}

	private void init() {
		dateField.setDate(LocalDate.now());
		formattedTextField.setValue(Date.valueOf(LocalDate.of(2016, 4, 12)));
	}

	/**
	 * Create the frame.
	 */
	public FieldTestsDevMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDatefield = new JLabel("dateField");
		lblDatefield.setBounds(10, 11, 46, 14);
		panel.add(lblDatefield);
		
		dateField = new DateField(dateFormat);
		dateField.addPropertyChangeListener("value", new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent evt) {
	        	onChanged();
	        }
	    });

		dateField.setBounds(10, 27, 174, 20);
		panel.add(dateField);
		
		formattedTextField = new JFormattedTextField(dateFormat);
		formattedTextField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
//				onChanged();
			}

		});
		
		lblFormattedtextfield = new JLabel("formattedTextField");
		lblFormattedtextfield.setBounds(10, 79, 174, 14);
		panel.add(lblFormattedtextfield);
		formattedTextField.setBounds(10, 97, 174, 20);
		panel.add(formattedTextField);
		
		JButton btnNewButton = new JButton("Set");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextField.setValue(Date.valueOf(LocalDate.of(2016, 4, 18)));
				dateField.setDate((LocalDate.of(2016, 4, 1)));
			}
		});
		btnNewButton.setBounds(10, 140, 89, 23);
		panel.add(btnNewButton);
		
		JButton button = new JButton("Get");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printValues();
			}
		});
		button.setBounds(121, 140, 89, 23);
		panel.add(button);
	}

	protected void printValues() {
		System.out.println(dateField.getDate());
//		System.out.println(formattedTextField.getValue());
	}
	private void onChanged() {
		System.out.println("onChanged"+dateField.getDate());
	}
}
