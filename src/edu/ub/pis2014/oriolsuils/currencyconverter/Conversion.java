package edu.ub.pis2014.oriolsuils.currencyconverter;

import java.io.Serializable;

/*
 * This class represents a currency conversion, that includes the type of currency and the conversion 
 */
public class Conversion implements Serializable{
	
	private static final long serialVersionUID = 1871438087626016737L;
	private double conversion;
	//The currency is represented by an int which can be 0(dollars) or 1(euro).
	private int currency;
	
	/*
	 * The empty constructor set the 2 variables to default values
	 */
	public Conversion(){
		setConversion(0);
		setCurrency(0);
	}
	
	public Conversion(double conversion, int currency){
		this.setConversion(conversion);
		this.setCurrency(currency);
	}

	/*
	 * Getters and setters
	 */
	
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
