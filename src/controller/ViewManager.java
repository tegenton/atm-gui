package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import view.*;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds

	public BankAccount getAccount() {
		return account;
	}
	
	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param views
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void login(String accountNumber, char[] pin) {
		LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
		
		try {
			account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
			
			if (account == null) {
				lv.updateErrorMessage("Invalid account number and/or PIN.");
			} else {
				((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]).updateAccountMessage();
				switchTo(ATM.HOME_VIEW);
				lv.clear();
			}
		} catch (NumberFormatException e) {
			lv.updateErrorMessage("Account numbers and PINs don't have letters.");
		}
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	public void logout() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Log out of ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.updateAccount(account);
				account = null;
				this.switchTo(ATM.LOGIN_VIEW);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getMaxAccountNumber() {
		try {
			return db.getMaxAccountNumber();
		} catch (SQLException e) {
			return -1;
		}
	}

	public void addAccount(BankAccount account) {
		db.insertAccount(account);
		login(String.valueOf(account.getAccountNumber()), String.valueOf(account.getUser().getPin()).toCharArray());
	}

	public int deposit(double amount) {
		return account.deposit(amount);
	}

	public int withdraw(double amount) {
		return account.withdraw(amount);
	}
}
