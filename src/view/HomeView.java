package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;

import javax.swing.*;

import controller.ViewManager;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	/*a welcome message unique to the user (first and last name)
a label indicating the user's current account balance (formatted as $###,###.##)
a button to initiate a deposit
a button to initiate a withdrawal
a button to initiate a transfer
a button to view/edit his or her personal information
a button to close the account
a button to logout*/
	private JLabel accountMessage;
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton transferButton;
	private JButton editInfoButton;
	private JButton closeAccountButton;
	private JButton logoutButton;

	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the HomeView.
		// temp thing
		this.initAccountMessage();
		
		// TODO
		//
		// this is where you should build the HomeView (i.e., all the components that
		// allow the user to interact with the ATM - deposit, withdraw, transfer, etc.).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
		this.initLogoutButton();
		this.initDepositButton();
		this.initWithdrawButton();
		this.initTransferButton();
		this.initEditButton();
		this.initCloseAccountButton();
	}

	private void initAccountMessage() {
		accountMessage = new JLabel("", SwingConstants.CENTER);
		accountMessage.setBounds(50, 50, 500, 50);
		this.add(accountMessage);
	}

	private void initDepositButton() {
		depositButton = new JButton("Deposit");
		depositButton.setBounds(50, 100, 100, 100);
		depositButton.addActionListener(this);
		this.add(depositButton);
	}

	private void initWithdrawButton () {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(200, 100, 100, 100);
		withdrawButton.addActionListener(this);
		this.add(withdrawButton);
	}

	private void initTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(50, 250, 100, 100);
		transferButton.addActionListener(this);
		this.add(transferButton);
	}

	private void initEditButton() {
		editInfoButton = new JButton("Acct. Info");
		editInfoButton.setBounds(200, 250, 100, 100);
		editInfoButton.addActionListener(this);
		this.add(editInfoButton);
	}

	private void initCloseAccountButton() {
		closeAccountButton = new JButton("Close Acct.");
		closeAccountButton.setBounds(350, 250, 100, 100);
		closeAccountButton.addActionListener(this);
		this.add(closeAccountButton);
	}

	private void initLogoutButton() {
		logoutButton = new JButton("Log Out");
		logoutButton.setBounds(5, 5, 100, 50);
		logoutButton.addActionListener(this);
		this.add(logoutButton);
	}

	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
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
		
		if (source.equals(logoutButton)) {
			manager.logout();
		}
		else if (source.equals(depositButton)) {
			deposit();
			updateAccountMessage();
		}
		else if (source.equals(withdrawButton)) {
			withdraw();
			updateAccountMessage();
		}
		else if (source.equals(transferButton)) {
			transfer();
			updateAccountMessage();
		}
		else if (source.equals(editInfoButton)) {
			manager.editInfo();
		}
		else if (source.equals(closeAccountButton)) {
			manager.closeAccount();
		}
	}

	private void deposit() {
		try {
			double amount = Double.parseDouble(JOptionPane.showInputDialog("How much are you depositing?"));
			if (manager.deposit(amount) != ATM.SUCCESS) {
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid amount", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void withdraw() {
		try {
			double amount = Double.parseDouble(JOptionPane.showInputDialog("How much are you withdrawing?"));
			if (manager.withdraw(amount) != ATM.SUCCESS) {
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid amount", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
		}
		catch (NullPointerException e) {

		}
	}

	private void transfer() {
		try {
			JTextField targetAccount = new JTextField();
			JTextField amount = new JTextField();

			JPanel transferInput = new JPanel();
			transferInput.add(new JLabel("Account Number:"));
			transferInput.add(targetAccount);
			transferInput.add(new JLabel("Amount:"));
			transferInput.add(amount);

			int result = JOptionPane.showConfirmDialog(null, transferInput, "Transfer Funds", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				manager.transfer(Long.parseLong(targetAccount.getText()), Double.parseDouble(amount.getText()));
			}

		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid amount", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void updateAccountMessage() {
		DecimalFormat money = new DecimalFormat("$###,###.##");

		accountMessage.setText(
			manager.getAccount().getUser().getFirstName() + " " + manager.getAccount().getUser().getLastName() + "; " +
				manager.getAccount().getAccountNumber() + "; " +
				money.format(manager.getAccount().getBalance()));
	}
}
