package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField fnameField;
	private JTextField lnameField;
	private JFormattedTextField dobPicker; // date of birth
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField postalField;
	private JPasswordField pinField;	// desired pin
	private JButton clearButton;
	private JButton submitButton;
	private JButton cancelButton;
	// TODO: limit to proper number of characters
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the CreateView.
		
		this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
		this.initFnameField();
		this.initLnameField();
		this.initDobPicker(); // date of birth
		this.initPhoneField();
		this.initAddressFields();
		this.initPinField(); // desired pin
		this.initClearButton(); // TODO: clear all text fields
		this.initSubmitButton(); // TODO: submit function
		this.initCancelButton();
	}

	private void initFnameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 135, 95, 35);

		fnameField = new JTextField();
		fnameField.setBounds(250, 135, 100, 35);
		addKeyListener(fnameField, 15);

		this.add(label);
		this.add(fnameField);
	}

	private void initLnameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 170, 95, 35);

		lnameField = new JTextField();
		lnameField.setBounds(250, 170, 100, 35);
		addKeyListener(lnameField, 15);


		this.add(label);
		this.add(lnameField);
	}

	private void initDobPicker() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(100, 205, 95, 35);

	    try {
			MaskFormatter dateFormat = new MaskFormatter("##/##/####");
			dateFormat.setPlaceholderCharacter('_');
			dobPicker = new JFormattedTextField(dateFormat);
		} catch (ParseException e) {
			dobPicker.setText("");
		}
		dobPicker.setBounds(250, 205, 100, 35);

		this.add(label);
	    this.add(dobPicker);
	}

	private void initPhoneField() {
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(100, 240, 95, 35);

		try {
			MaskFormatter phoneFormat = new MaskFormatter("(###) ###-####");
			phoneFormat.setPlaceholderCharacter('_');
			phoneField = new JFormattedTextField(phoneFormat);
		} catch (ParseException e) {
			phoneField.setText("");
		}
		phoneField.setBounds(250, 240, 100, 35);

		this.add(label);
		this.add(phoneField);
	}

	private void initAddressFields() {
		JLabel label = new JLabel("Street Address", SwingConstants.RIGHT);
		label.setBounds(100, 275, 95, 35);

		addressField = new JTextField();
		addressField.setBounds(250, 275, 100, 35);
		addKeyListener(addressField, 30);

		this.add(label);
		this.add(addressField);

		label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(100, 310, 95, 35);

		cityField = new JTextField();
		cityField.setBounds(250, 310, 100, 35);
		addKeyListener(cityField, 30);

		this.add(label);
		this.add(cityField);

		label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 345, 95, 35);

		stateField = new JTextField();
		stateField.setBounds(250, 345, 100, 35);
		addKeyListener(stateField, 2);

		this.add(label);
		this.add(stateField);

		label = new JLabel("Postal Code", SwingConstants.RIGHT);
		label.setBounds(100, 380, 95, 35);

		postalField = new JTextField();
		postalField.setBounds(250, 380, 100, 35);
		addKeyListener(postalField, 5);

		this.add(label);
		this.add(postalField);
	}

	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(100, 415, 95, 35);

		pinField = new JPasswordField();
		pinField.setBounds(250, 415, 100, 35);
		addKeyListener(pinField, 4);

		this.add(label);
		this.add(pinField);
	}

	private void initClearButton() {
		clearButton = new JButton("Clear");
		clearButton.setBounds(395,5,100,50);
		clearButton.addActionListener(this);
		this.add(clearButton);
	}

	private void initSubmitButton() {
		submitButton = new JButton("Submit");
		submitButton.setBounds(200,5,100,50);
		submitButton.addActionListener(this);
		this.add(submitButton);
	}
	
	private void initCancelButton() {
		cancelButton = new JButton("Back");
		cancelButton.setBounds(5, 5, 100, 50);
		cancelButton.addActionListener(this);
		this.add(cancelButton);
	}
	
	private void addKeyListener(JTextField field, int length) {
		field.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        if (field.getText().length() >= length) // limit account number to 9 characters
		            e.consume();
		    }
		});

	}

	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 *
	 * @param oos
	 * @throws IOException
	 */

	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
		
		Object source = e.getSource();
		
		if (source.equals(cancelButton)) {
			manager.switchTo(ATM.LOGIN_VIEW);
		}
	}
}
