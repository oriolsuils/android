package edu.ub.pis2014.currencyconverter;

import java.io.Serializable;

public class conversion implements Serializable{
	
	private double conversion;
	private int currency;
	
	public conversion(){
		setConversion(0);
		setCurrency(0);
	}
	
	public conversion(double amount, double conversion, int currency){
		this.setConversion(conversion);
		this.setCurrency(currency);
	}

	public double getConversion() {
		return conversion;
	}

	public void setConversion(double conversion) {
		this.conversion = conversion;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}
	
	
}
