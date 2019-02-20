package com.capg.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capg.beans.Customer;
import com.capg.beans.Wallet;
import com.capg.exceptions.InsufficientBalance;
import com.capg.exceptions.MobileNumberDoesNotExist;
import com.capg.beans.*;
import com.capg.beans.Customer;
public class WalletRepoImpl implements WalletRepo {
	EntityManager manager;
	
	public WalletRepoImpl()
	{
		super();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("customer");
		manager=emf.createEntityManager();
	}
	@Override
	public void save(Customer customer) {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
	}

	@Override
	public Customer updateAccount(String mobileno,BigDecimal amount)
		 {
		manager.getTransaction().begin();
		Customer customer=manager.find(Customer.class,mobileno);
		Wallet wallet=customer.getWallet();
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		manager.getTransaction().commit();
		return customer;
		}	
	public Customer findOne(String mobileno)
	{
		manager.getTransaction().begin();
		Customer customer=manager.find(Customer.class,mobileno);
		manager.getTransaction().commit();
		return customer;
	}
}
