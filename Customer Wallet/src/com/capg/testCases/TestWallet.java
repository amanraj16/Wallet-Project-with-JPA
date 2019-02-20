package com.capg.testCases;


import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Test;

import com.capg.exceptions.InsufficientBalance;
import com.capg.exceptions.MobileNumberIsAlreadyRegistered;
import com.capg.exceptions.MobileNumberDoesNotExist;
import com.capg.service.WalletServiceImpl;

public class TestWallet {
	WalletServiceImpl walletService=new WalletServiceImpl();
	@Test(expected=com.capg.exceptions.MobileNumberDoesNotExist.class)
	public void whenMobileNumberDoesNotExistWhileDepositingAmount()throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException, MobileNumberIsAlreadyRegistered {
		BigDecimal amount=new BigDecimal(1000);
		walletService.depositAmount("7060608502",amount);
	}
	@Test(expected=com.capg.exceptions.MobileNumberIsAlreadyRegistered.class)
	public void whenAllEntriesAreCorrectWhileCreatingNewAccount()throws MobileNumberIsAlreadyRegistered, ClassNotFoundException, SQLException{
		
		BigDecimal amount=new BigDecimal(2000);
		walletService.createAccount("Aman","7060608501",amount);
		walletService.createAccount("Aman","7060608501",amount);
		
	}
	@Test(expected=com.capg.exceptions.MobileNumberDoesNotExist.class)
	public void whenMobileNoDoesnotExistWhileWithdrawingAmount()throws MobileNumberDoesNotExist, InsufficientBalance, ClassNotFoundException, SQLException
	{
		
		BigDecimal amount=new BigDecimal(1000);
		walletService.withdrawAmount("7060608502", amount);
	}
	@Test(expected=com.capg.exceptions.InsufficientBalance.class)
	public void whenBalanceIsNotSufficientWhileTransferingFunds()throws InsufficientBalance, MobileNumberDoesNotExist, MobileNumberIsAlreadyRegistered, ClassNotFoundException, SQLException
	{
		
		BigDecimal amount=new BigDecimal(20000);
		BigDecimal amount1=new BigDecimal(30000);
		walletService.fundTransfer("7060608501", "7599260545", amount1);
	}
	@Test(expected=com.capg.exceptions.MobileNumberDoesNotExist.class)
	public void whenMobileNoDoesNotExistWhileBalanceShow()throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException
	{
		
		walletService.showBalance("7060608502");
	}
	@Test(expected=com.capg.exceptions.MobileNumberDoesNotExist.class)
	public void whenSenderMobileNoDoesNotExistWhileFundsTransfer()throws MobileNumberDoesNotExist,  MobileNumberIsAlreadyRegistered, InsufficientBalance, ClassNotFoundException, SQLException
	{
		
		BigDecimal amount1=new BigDecimal(200);
		walletService.fundTransfer("9876543210","7599260545", amount1);	
	}
	@Test(expected=com.capg.exceptions.MobileNumberDoesNotExist.class)
	public void whenReceiverMobileNoDoesNotExistWhileFundsTransfer()throws MobileNumberDoesNotExist,  MobileNumberIsAlreadyRegistered, InsufficientBalance, ClassNotFoundException, SQLException
	{
		
		BigDecimal amount=new BigDecimal(1000);
		BigDecimal amount1=new BigDecimal(100);
		walletService.fundTransfer("7599260545","9999999999", amount1);	
	}
	@Test(expected=com.capg.exceptions.InsufficientBalance.class)
	public void whenRemainingBalanceIsInsufficientWhileWithdrawingAmount()throws MobileNumberDoesNotExist, InsufficientBalance, MobileNumberIsAlreadyRegistered, ClassNotFoundException, SQLException
	{
	
		BigDecimal amount1=new BigDecimal(300000);
		walletService.withdrawAmount("7599260545", amount1);
	}

}
