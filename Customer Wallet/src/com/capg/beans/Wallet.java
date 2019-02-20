package com.capg.beans;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Wallet {
	private BigDecimal balance;

	public Wallet(BigDecimal balance) {
		super();
		this.balance = balance;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
