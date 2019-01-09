package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.dao.SavingsAccountDAO;
import com.cg.app.account.factory.AccountFactory;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InvalidInputException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;
	
	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		
	}

	@Override
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}
	
	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}

	@Transactional(rollbackForClassName= {"Throwable"})
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		
		deposit(receiver, amount);
		withdraw(sender, amount);
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.updateAccount(account);
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, InvalidInputException{
		return savingsAccountDAO.deleteAccount(accountNumber);
		
	}

	@Override
	public double checkAccountBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, InvalidInputException {
		return savingsAccountDAO.checkAccountBalance(accountNumber);
		
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccountsSortedByNames() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllAccountsSortedByNames();
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccountsSortedByRange(
			double minimum, double maximum) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccountsSortedByRange(minimum,maximum);
	
	}

	@Override
	public SavingsAccount getAccountHolderName(String accountToSearch)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountByName(accountToSearch);
	}

	@Override
	public List<SavingsAccount> getAccountByBalanceRange(double minimumBalance, double highestBalance)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountByBalanceRange(minimumBalance, highestBalance);
	}

	@Override
	public List<SavingsAccount> sort(int choice) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sort(choice);
		
	}

}
