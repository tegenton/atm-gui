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
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class InformationView extends JPanel implements ActionListener {

	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField fnameField;
	private JTextField lnameField;
	private JTextField dobPicker; // date of birth
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField cityField;
	private JComboBox<String> stateField;
	private JTextField postalField;
	private JButton changePin;	// desired pin
	private JButton editButton;
	private JButton cancelButton;
	private int updatedPin;
	private JLabel errorMessage;

	/**
	 * Constructs an instance (or object) of the CreateView class.
	 *
	 * @param manager
	 */

	public InformationView(ViewManager manager) {
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
		this.initEditButton(); // TODO: submit function
		this.initCancelButton();

		errorMessage = new JLabel("");
		errorMessage.setBounds(200,50,100,50);
		this.add(errorMessage);

		clear();
	}

	private void initFnameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 135, 95, 35);

		fnameField = new JTextField();
		fnameField.setBounds(250, 135, 100, 35);
		fnameField.setEditable(false);

		this.add(label);
		this.add(fnameField);
	}

	private void initLnameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 170, 95, 35);

		lnameField = new JTextField();
		lnameField.setBounds(250, 170, 100, 35);
		lnameField.setEditable(false);

		this.add(label);
		this.add(lnameField);
	}

	private void initDobPicker() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(100, 205, 95, 35);

		dobPicker = new JTextField();
		dobPicker.setBounds(250,205,100,35);
		dobPicker.setEditable(false);

		this.add(label);
		this.add(dobPicker);
	}

	private void initPhoneField() {
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(100, 240, 95, 35);

		phoneField = new JTextField();
		phoneField.setBounds(250, 240, 100, 35);
		phoneField.setEditable(false);

		this.add(label);
		this.add(phoneField);
	}

	private void initAddressFields() {
		JLabel label = new JLabel("Street Address", SwingConstants.RIGHT);
		label.setBounds(100, 275, 95, 35);

		addressField = new JTextField();
		addressField.setBounds(250, 275, 100, 35);
		addressField.setEditable(false);

		this.add(label);
		this.add(addressField);

		label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(100, 310, 95, 35);

		cityField = new JTextField();
		cityField.setBounds(250, 310, 100, 35);
		cityField.setEditable(false);

		this.add(label);
		this.add(cityField);

		label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 345, 95, 35);

		// list of states
		String[] states = {"AL",
			"AK",
			"AZ",
			"AR",
			"CA",
			"CO",
			"CT",
			"DE",
			"FL",
			"GA",
			"HI",
			"ID",
			"IL",
			"IN",
			"IA",
			"KS",
			"KY",
			"LA",
			"ME",
			"MD",
			"MA",
			"MI",
			"MN",
			"MS",
			"MO",
			"MT",
			"NE",
			"NV",
			"NH",
			"NJ",
			"NM",
			"NY",
			"NC",
			"ND",
			"OH",
			"OK",
			"OR",
			"PA",
			"RI",
			"SC",
			"SD",
			"TN",
			"TX",
			"UT",
			"VT",
			"VA",
			"WA",
			"WV",
			"WI",
			"WY"
		};
		stateField = new JComboBox<>(states);
		stateField.setBounds(250, 345, 100, 35);
		stateField.setEditable(false);

		this.add(label);
		this.add(stateField);

		label = new JLabel("Postal Code", SwingConstants.RIGHT);
		label.setBounds(100, 380, 95, 35);

		postalField = new JTextField();
		postalField.setBounds(250, 380, 100, 35);
		postalField.setEditable(false);

		this.add(label);
		this.add(postalField);
	}

	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(100, 415, 95, 35);

		changePin = new JButton("Change");
		changePin.setBounds(250, 415, 100, 35);
		changePin.addActionListener(this);

		this.add(label);
		this.add(changePin);
	}

	private void initEditButton() {
		editButton = new JButton("Edit Info");
		editButton.setBounds(200,5,100,50);
		editButton.addActionListener(this);
		this.add(editButton);
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
			clear();
			updateInfo();
			disableEditing();
			manager.switchTo(ATM.HOME_VIEW);
		}
		else if (source.equals(editButton)) {
			if (editButton.getText().equals("Save")) {
				try {
					saveInfo();
					disableEditing();
					clear();
					updateInfo();
				}
				catch (Exception f) {
					clear();
					updateInfo();
					enableEditing();
					errorMessage.setText("Invalid info");
				}
			}
			else {
				enableEditing();
			}
		}
		else if (source.equals(changePin)) {
			int pinGuess = Integer.parseInt(JOptionPane.showInputDialog("What is your current PIN?"));
			if (manager.getAccount().getUser().getPin() == pinGuess) {
				updatedPin = Integer.parseInt(JOptionPane.showInputDialog("What is your new PIN?"));
			}
		}
	}

	private void enableEditing() {
		editButton.setText("Save");

		phoneField.setEditable(true);

		addressField.setEditable(true);
		cityField.setEditable(true);
		stateField.setEditable(true);
		postalField.setEditable(true);

		updatedPin = manager.getAccount().getUser().getPin();
	}

	private void disableEditing() {
		editButton.setText("Edit Info");

		phoneField.setEditable(false);

		addressField.setEditable(false);
		cityField.setEditable(false);
		postalField.setEditable(false);
	}

	private void clear() {
		fnameField.setText("");
		lnameField.setText("");
		dobPicker.setText(""); // date of birth
		phoneField.setText("");
		addressField.setText("");
		cityField.setText("");
		stateField.setSelectedItem("Choose One");
		postalField.setText("");

		errorMessage.setText("");
	}

	public void updateInfo() {
		fnameField.setText(manager.getAccount().getUser().getFirstName());
		lnameField.setText(manager.getAccount().getUser().getLastName());
		dobPicker.setText(manager.getAccount().getUser().getFormattedDob()); // date of birth
		phoneField.setText(manager.getAccount().getUser().getFormattedPhone());
		addressField.setText(manager.getAccount().getUser().getStreetAddress());
		cityField.setText(manager.getAccount().getUser().getCity());
		stateField.setSelectedItem(manager.getAccount().getUser().getState());
		postalField.setText(manager.getAccount().getUser().getZip());
	}

	public void saveInfo() {
		User temp = manager.getAccount().getUser();

		String strPhone = phoneField.getText();
		Long phone = Long.parseLong(strPhone.substring(1,4) + strPhone.substring(6,9) + strPhone.substring(10, 14));

		temp.setPhone(phone);

		temp.setStreetAddress(addressField.getText());
		temp.setCity(cityField.getText());
		temp.setState(stateField.getSelectedItem().toString());
		temp.setZip(postalField.getText());

		if (temp.getPin() != updatedPin) {
			temp.setPin(temp.getPin(), updatedPin);
		}

		manager.setAccountUser(temp);
	}
}
