package com.capg.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capg.beans.Customer;
import com.capg.exceptions.InsufficientBalance;
import com.capg.exceptions.MobileNumberDoesNotExist;
import com.capg.exceptions.MobileNumberIsAlreadyRegistered;

public interface WalletService {
	public Customer createAccount(String name,String mobileNo,BigDecimal amount) throws MobileNumberIsAlreadyRegistered, ClassNotFoundException, SQLException;
	public Customer showBalance(String mobileNo) throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException;
	public Customer fundTransfer(String sourceMobileNo,String targetMobileNo,BigDecimal amount) throws InsufficientBalance, MobileNumberDoesNotExist, SQLException, ClassNotFoundException;
	public Customer depositAmount(String mobileNo,BigDecimal amount) throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException;
	public Customer withdrawAmount(String mobileNo,BigDecimal amount) throws InsufficientBalance, MobileNumberDoesNotExist, ClassNotFoundException, SQLException;
	public Customer searchMobileNumber(String mobileno)throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException;
}
