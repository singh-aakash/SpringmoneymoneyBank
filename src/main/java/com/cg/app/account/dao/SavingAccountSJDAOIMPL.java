package com.cg.app.account.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InvalidInputException;
@Repository
@Primary
public class SavingAccountSJDAOIMPL implements SavingsAccountDAO{

	@Autowired
	private JdbcTemplate jdbctemplate;
	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		jdbctemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",new Object[] {
				account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(),
				account.isSalary(),null,"SA"});
		return account;
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount account) throws SQLException, ClassNotFoundException {
		jdbctemplate.update("UPDATE account SET  account_hn = ?,salary =? WHERE account_id = ?", new Object[] 
				{account.getBankAccount().getAccountHolderName(),account.isSalary(), account.getBankAccount().getAccountNumber() });
		return null;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return jdbctemplate.queryForObject("SELECT * from account WHERE account_id=?",new Object[] {accountNumber}, new SavingsAccountMap());
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber)
			throws SQLException, ClassNotFoundException, InvalidInputException {
		jdbctemplate.update("DELETE FROM account WHERE account_id = ?", accountNumber);
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		List<SavingsAccount> list = jdbctemplate.query("SELECT * FROM ACCOUNT", new SavingsAccountMap());
		return list;
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		
		jdbctemplate.update("UPDATE ACCOUNT SET account_bal=? where account_id=?", new Object[] {currentBalance, accountNumber});
		
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double checkAccountBalance(int accountNumber)
			throws SQLException, ClassNotFoundException, InvalidInputException {
		return jdbctemplate.update("select account_bal from account where account_id=?",accountNumber);
	}

	@Override
	public List<SavingsAccount> getAllAccountsSortedByNames() throws ClassNotFoundException, SQLException {
		List<SavingsAccount> list = jdbctemplate.query("select * from account order by account_hn",new SavingsAccountMap());
		return list;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccountsSortedByRange(double minimum, double maximum)
			throws ClassNotFoundException, SQLException {
		return null;
	}

	@Override
	public List<SavingsAccount> getAccountByBalanceRange(double minimumBalance, double highestBalance)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		 List<SavingsAccount> list =jdbctemplate.query("SELECT * FROM ACCOUNT ORDER BY account_bal",new SavingsAccountMap());
			return list;
	}

	@Override
	public SavingsAccount getAccountByName(String accountToSearch)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		jdbctemplate.update("select * from account where account_hn=?",accountToSearch, new SavingsAccountMap());
		return null;
	}

	@Override
	public List<SavingsAccount> sort(int choice) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
