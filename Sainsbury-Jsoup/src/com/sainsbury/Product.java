package com.sainsbury;

import java.math.BigDecimal;

public class Product {
	private String title;
	private int kcalPer100g;
	private BigDecimal unitPrice;
	private String description;
	
	public Product(String title,int kcal,BigDecimal unitP, String desc){
		this.title = title;
		kcalPer100g = kcal;
		unitPrice = unitP;
		description = desc;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getKcalPer100g() {
		return kcalPer100g;
	}
	public void setKcalPer100g(int kcalPer100g) {
		this.kcalPer100g = kcalPer100g;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		
		if(kcalPer100g != 0){
		return "*********************\nTitle : "+title+"\nkCal per 100g : "+kcalPer100g+"\nUnit Price : "+unitPrice+"\nDescription : "+description;
		}
		else{
			return "*********************\nTitle : "+title+"\nUnit Price : "+unitPrice+"\nDescription : "+description;
		}
	}
	
	
	
	
}
