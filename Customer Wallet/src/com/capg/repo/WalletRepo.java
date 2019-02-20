package com.capg.repo;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capg.beans.Customer;
import com.capg.exceptions.InsufficientBalance;
import com.capg.exceptions.MobileNumberDoesNotExist;

public interface WalletRepo {
	public void save(Customer customer) throws ClassNotFoundException, SQLException;
	public Customer updateAccount(String mobileno,BigDecimal amount);
	public Customer findOne(String mobileno) throws ClassNotFoundException, SQLException;
}
