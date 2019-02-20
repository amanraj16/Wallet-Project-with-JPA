package com.capg.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.capg.beans.Customer;
import com.capg.exceptions.MobileNumberDoesNotExist;
import com.capg.exceptions.MobileNumberIsAlreadyRegistered;
import com.capg.service.WalletServiceImpl;

public class Test {
	private static Scanner scanner=new Scanner(System.in);
	private static WalletServiceImpl walletServiceImpl=new WalletServiceImpl();
	public static void main(String[] args) throws ClassNotFoundException, MobileNumberDoesNotExist, SQLException {
		showMenu();
	}
	public static  void showMenu() throws ClassNotFoundException, MobileNumberDoesNotExist, SQLException
	{
		int choice;
		while(true)
		{
			System.out.println("Press 1 to CREATE ACCOUNT");
			System.out.println("Press 2 to SHOW BALANCE");
			System.out.println("Press 3 to FUND TRANSFER");
			System.out.println("Press 4 to WITHDRAW AMOUNT");
			System.out.println("Press 5 to DEPOSIT AMOUNT");
			System.out.println("Press 6 to EXIT THE SYSTEM");
			System.out.println();
			System.out.println("Enter Your Choice");
			choice=scanner.nextInt();
			switch(choice)
			{
			case 1:createAccount();
				   break;
			case 2:showBalance();
				   break;
			case 3:fundTransfer();
				   break;
			case 4:withdrawAmount();
				   break;
			case 5:depositAmount();
			 	   break;
			case 6:System.exit(0);
			default:System.out.println("Wrong Choice Is Entered");
			}
		}
	}
	private static void depositAmount() {
		System.out.println("Please Enter Your Mobile Number");
		String mobileNo=scanner.next();
		int len=mobileNo.length();
		if(len!=10)
		{
			System.out.println("Please Enter 10 digit Mobile Number");
			depositAmount();
		}
		else if(Pattern.matches("[6789]{1}[0-9]{9}",mobileNo)==false)
			{
			System.out.println("This mobile number is not valid");
			depositAmount();
			}
		try {
			Customer customer=walletServiceImpl.searchMobileNumber(mobileNo);
		}catch(Exception e)
		{
			System.out.println("***"+e.getMessage()+"***"+"\n\n");
		}
		System.out.println("Enter Amount to deposit");
		BigDecimal amount=scanner.nextBigDecimal();
		try {
			Customer customer=walletServiceImpl.depositAmount(mobileNo, amount);
			System.out.println("Transaction Successfull. Updated Balance--> "+customer.getWallet().getBalance()+"\n\n");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	private static void withdrawAmount() {
		System.out.println("Please Enter Your Mobile Number");
		String mobileNo=scanner.next();
		int len=mobileNo.length();
		if(len!=10)
		{
			System.out.println("Please Enter 10 digit Mobile Number");
		}
		else if(Pattern.matches("[6789]{1}[0-9]{9}",mobileNo)==false)
			{
			System.out.println("This mobile number is not valid");
			}
		try {
			Customer customer=walletServiceImpl.searchMobileNumber(mobileNo);
		}catch(Exception e)
		{
			System.out.println("***"+e.getMessage()+"***"+"\n\n");
		}
		System.out.println("Enter Amount to withdraw");
		BigDecimal amount=scanner.nextBigDecimal();
		try {
			Customer customer=walletServiceImpl.withdrawAmount(mobileNo, amount);
			System.out.println("Transaction Successfull. Remaining Balance--> "+customer.getWallet().getBalance()+"\n\n");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	private static void fundTransfer() {
		System.out.println("Enter Source Mobile Number");
		String smobileno=scanner.next();
		if(smobileno.length()!=10)
		{
			System.out.println("Please Enter 10 digit Mobile Number");
		}
		else if(Pattern.matches("[6789]{1}[0-9]{9}",smobileno)==false)
			{
			System.out.println("This mobile number is not valid");
			}
		try {
			Customer sourceCustomer=walletServiceImpl.searchMobileNumber(smobileno);
		}catch(Exception e)
		{
			System.out.println("***"+e.getMessage()+"***"+"\n\n");
			System.exit(0);
		}
		System.out.println("Enter Target Mobile Number");
		String tmobileno=scanner.next();
		if(tmobileno.length()!=10)
		{
			System.out.println("Please Enter 10 digit Mobile Number");
		}
		else if(Pattern.matches("[6789]{1}[0-9]{9}",tmobileno)==false)
			{
			System.out.println("This mobile number is not valid");
			}
		try {
			Customer targetCustomer=walletServiceImpl.searchMobileNumber(tmobileno);
		}catch(Exception e)
		{
			System.out.println("***"+e.getMessage()+"***"+"\n\n");
			System.exit(0);
		}
		System.out.println("Enter Amount to tansfer");
		BigDecimal amount=scanner.nextBigDecimal();
		try {
			Customer targetCustomer=walletServiceImpl.searchMobileNumber(tmobileno);
			Customer sourceCustomer=walletServiceImpl.searchMobileNumber(smobileno);
			Customer customer=walletServiceImpl.fundTransfer(smobileno, tmobileno, amount);
			System.out.println("Amount = "+amount+" is successfully transfered to "+targetCustomer.getName()+"("+targetCustomer.getMobileNo()+"). Remaining Balance = "+customer.getWallet().getBalance()+"\n\n");
		}catch(Exception e)
		{
			System.out.println("***"+e.getMessage()+"***"+"\n\n");
		}
	}
	public static void createAccount() throws ClassNotFoundException, MobileNumberDoesNotExist, SQLException
	{
		System.out.println("Please Enter Your Name");
		String name=scanner.next();
		System.out.println("Please Enter Your Mobile Number");
		String mobileNo=scanner.next();
		int len=mobileNo.length();
		if(len!=10)
		{
			System.out.println("Please Enter 10 digit Mobile Number");
			createAccount();
		}
		else if(Pattern.matches("[6789]{1}[0-9]{9}",mobileNo)==false)
			{
			System.out.println("This mobile number is not valid");
			createAccount();
			}
		else
		{
			System.out.println("Please Enter Wallet Amount");
			BigDecimal amount=scanner.nextBigDecimal();
			try {
					Customer cust=walletServiceImpl.createAccount(name, mobileNo, amount);
					System.out.println("HURRAY....Your Account has been created...Thankyou for being a part of us");
					System.out.println("Your Details are as follows-->");
					System.out.println("NAME -: "+cust.getName());
					System.out.println("MOBILE NUMBER -: "+cust.getMobileNo());
					System.out.println("WALLET BALANCE -: "+cust.getWallet().getBalance());
					System.out.println();
				}catch(Exception e)
				{
					System.out.println("***"+e.getMessage()+"***"+"\n\n");
				}
	}
	}

	public static void showBalance() throws ClassNotFoundException, MobileNumberDoesNotExist, SQLException
	{
		System.out.println("Enter Mobile Number");
		String mobileno=scanner.next();
		if(mobileno.length()!=10)
		{
			System.out.println("Please Enter 10 digit Mobile Number");
			createAccount();
		}
		else if(Pattern.matches("[6789]{1}[0-9]{9}",mobileno)==false)
			{
			System.out.println("This mobile number is not valid");
			createAccount();
			}
		else
		{
			try {
				Customer customer=walletServiceImpl.showBalance(mobileno);
				System.out.println("Your Balance Is :- "+customer.getWallet().getBalance()+"\n\n");
			}catch(Exception e)
			{
				System.out.println("***"+e.getMessage()+"***"+"\n\n");
			}
	}
}
}
	
