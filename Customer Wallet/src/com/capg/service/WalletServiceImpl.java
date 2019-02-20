package com.capg.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capg.beans.Customer;
import com.capg.beans.Wallet;
import com.capg.exceptions.InsufficientBalance;
import com.capg.exceptions.MobileNumberDoesNotExist;
import com.capg.exceptions.MobileNumberIsAlreadyRegistered;
import com.capg.repo.WalletRepo;
import com.capg.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {
	WalletRepo walletRepo=new WalletRepoImpl();
	public WalletServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}

	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws MobileNumberIsAlreadyRegistered, ClassNotFoundException, SQLException {
		Customer customer=walletRepo.findOne(mobileNo);
		if(customer==null)
		{
			Customer customer1=new Customer();
			customer1.setMobileNo(mobileNo);
			customer1.setName(name);
			Wallet wallet=new Wallet();
			wallet.setBalance(amount);
			customer1.setWallet(wallet);
			walletRepo.save(customer1);
				return customer1;
		}
		else 
			throw new MobileNumberIsAlreadyRegistered("This Mobile Number Is Already Registered");
		
	}

	@Override
	public Customer showBalance(String mobileNo) throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException {
		Customer customer=walletRepo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
		throw new MobileNumberDoesNotExist("This Mobile Number Does Not Exist");
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InsufficientBalance, MobileNumberDoesNotExist, ClassNotFoundException, SQLException {
		Customer customer1=walletRepo.findOne(sourceMobileNo);
		if(customer1!=null)
		{
			Customer customer2=walletRepo.findOne(targetMobileNo);
			if(customer2!=null)
			{
				if(customer1.getWallet().getBalance().compareTo(amount)>=0)
				{
					Wallet sWallet=customer1.getWallet();
					Customer sCustomer=walletRepo.updateAccount(sourceMobileNo,sWallet.getBalance().subtract(amount));
					Wallet tWallet=customer2.getWallet();
					Customer tCustomer=walletRepo.updateAccount(targetMobileNo, tWallet.getBalance().add(amount));
					return sCustomer;
				}
				else
					throw new InsufficientBalance("Insufficient Balance In Your Account");
		}
			else
				throw new MobileNumberDoesNotExist("Target Mobile Number Does Not Exist");
		}
		else 
			throw new MobileNumberDoesNotExist("Source Mobile Number Does Not Exist");
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException {
		Customer customer=walletRepo.findOne(mobileNo);
		if(customer!=null)
		{
			Wallet wallet=customer.getWallet();
			return walletRepo.updateAccount(mobileNo, wallet.getBalance().add(amount));
		}
		else 
			throw new MobileNumberDoesNotExist("This Mobile Number Does Not Exist");
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientBalance, MobileNumberDoesNotExist, ClassNotFoundException, SQLException {
		Customer customer=walletRepo.findOne(mobileNo);
		if(customer!=null)
		{
			Wallet wallet=customer.getWallet();
			if(wallet.getBalance().compareTo(amount)>=0)
			{
				return walletRepo.updateAccount(mobileNo, wallet.getBalance().subtract(amount));
			}
			else
				throw new InsufficientBalance("Remaining Balance cannot be 0...Insufficient Balance");
		}
		else
			throw new MobileNumberDoesNotExist("This Mobile Number Does Not Exist");
	}
	public Customer searchMobileNumber(String mobileNo) throws MobileNumberDoesNotExist, ClassNotFoundException, SQLException
	{
		Customer customer=walletRepo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
			throw new MobileNumberDoesNotExist("This mobile number does not exist");
	}
}


