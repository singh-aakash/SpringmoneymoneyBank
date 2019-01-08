package com.cg.app.account.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.app.account.BankAccount;
import com.cg.app.account.SavingsAccount;

public class SavingsAccountMap implements RowMapper<SavingsAccount>{

	@Override
	public SavingsAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		SavingsAccount savingaccount = new SavingsAccount(rs.getInt("account_id"),rs.getString("account_hn"),rs.getDouble("account_bal"),rs.getBoolean("salary"));
		
		return savingaccount;
	}

}