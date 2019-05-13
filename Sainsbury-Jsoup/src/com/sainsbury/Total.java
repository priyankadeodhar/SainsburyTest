package com.sainsbury;

import java.math.BigDecimal;

public class Total {
	private BigDecimal gross;
	private BigDecimal vat;
	
	public Total(BigDecimal gross, BigDecimal vat){
		this.gross = gross;
		this.vat = vat;
	}
	
	public BigDecimal getGross() {
		return gross;
	}
	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	
}
