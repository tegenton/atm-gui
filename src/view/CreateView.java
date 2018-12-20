package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField fnameField;
	private JTextField lnameField;
	private JSpinner.DateEditor dobPicker; // date of birth
	private JTextField phoneField; // TODO: make this formatted OR (this should be separated into the 3 segments of a phone number)
	private JTextField addressField; // TODO: formatting 
	private JPasswordField pinField;	// desired pin
	private JButton submitButton;
	private JButton cancelButton;
	/*
	 * a textbox to enter his or her first name
a textbox to enter his or her last name
a date picker to select his or her date of birth
a textbox to enter his or her phone number
this should be separated into the 3 segments of a phone number
a textbox to enter his or her street address
a textbox to enter his or her city
a dropdown menu to select his or her state
a textbox to enter his or her postal code
a textbox to enter his or her desired PIN
this should be masked (much like a typical password field in a mobile/web application)
a button to create the account
a button to cancel and return to the LoginView
	 */
	
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
//		this.initFnameField();
//		this.initLnameField();
//		this.initDobPicker(); // date of birth
//		this.initPhoneField(); // TODO: make this formatted OR (this should be separated into the 3 segments of a phone number)
//		this.initAddressField(); // TODO: formatting 
//		this.initPinField();	// desired pin
//		this.initSubmitButton();
		this.initCancelButton();
	}
	
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void initCancelButton() {
		cancelButton = new JButton("Back");
		cancelButton.setBounds(5, 5, 100, 50);
		cancelButton.addActionListener(this);
		this.add(cancelButton);
	}

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