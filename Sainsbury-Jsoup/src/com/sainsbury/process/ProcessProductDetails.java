package com.sainsbury.process;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.sainsbury.Product;
import com.sainsbury.Total;

public interface ProcessProductDetails {
	BigDecimal VAT_PERCENTAGE = new BigDecimal(0.20);
	List<Product> createProductsListFromURL(String url) throws IOException;
	BigDecimal calculateGrossUnitPrice(List<Product> productList);
	BigDecimal calculateVat(BigDecimal gross);
	void convertToJson(List<Product> productList, Total total);
}
