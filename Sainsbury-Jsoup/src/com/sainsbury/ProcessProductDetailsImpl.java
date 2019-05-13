package com.sainsbury;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sainsbury.process.ProcessProductDetails;


public class ProcessProductDetailsImpl implements ProcessProductDetails{
	public static void main(String args[]){
		List<Product> productsList =null;
		try {
			//The Test URL
			String url ="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
			
			//Create an object
			ProcessProductDetailsImpl obj = new ProcessProductDetailsImpl();
			
			//Call the method to get the Products list from the test URL
			productsList = obj.createProductsListFromURL(url);
			
			//Calculate Gross
			BigDecimal gross = obj.calculateGrossUnitPrice(productsList);
			
			//Calculate Vat
			BigDecimal vat = obj.calculateVat(gross);
			
			//Create object of Total which contains "gross" and "vat" variables
			Total totalObj=new Total(gross,vat);
			
			//Finally Create JSON output 
			obj.convertToJson(productsList,totalObj);
		}
		catch (IOException e) {
			System.out.println("Error in reading the test URL"  );
			e.printStackTrace();
		}
		
		
	}
	
	public static Product fetchProductDetails(String productLinkUrl)throws IOException{
		Document doc = getDocument(productLinkUrl);
		
		//Get the title
		String title = doc.select("div.productTitleDescriptionContainer").select("h1").text();
		
		//Get the unit price
		String pricePerUnitText = doc.select("div.pricing").select("p.pricePerUnit").text();
		BigDecimal unitPrice = new BigDecimal(pricePerUnitText.substring(pricePerUnitText.indexOf("£")+1,pricePerUnitText.indexOf("/")));
		
		//Get the kCal per 100g
		int kCal =0;
		Elements temp = doc.select("table.nutritionTable").select("tr.tableRow0").select("td.nutritionLevel1");
		if(temp.eachText().size()>=1){
			String kCalStr=temp.eachText().get(0);
			kCal = Integer.parseInt(kCalStr.substring(0, kCalStr.toLowerCase().indexOf("k")));
		}
		
		//get the product Description
		String description="";
		List<String> descriptionList = doc.select("div.productText").select("p").eachText();
		for(String desc : descriptionList){
			if(!desc.isEmpty()){
				description = desc;
				break;
			}
		}
		//Create the Product object 
		return new Product(title,kCal,unitPrice,description);
		
	}
	//This method gets the HTML document object from the test URL
	public static Document getDocument(String url) throws IOException{
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		return doc;
	}

	@Override
	// This method will extract the required data from test URL by scanning each product link
	public List<Product> createProductsListFromURL(String url) throws IOException{
		//url is the test URL, using jsoup API get the Elements
		Elements temp= getDocument(url).select("div.productNameAndPromotions");
		List<Product> productsList = new ArrayList<>();
		
		for(Element productList	: temp){
			//Get the each product's link from the test URL
			Element productLinkURL = productList.selectFirst("a");
			//Get the absolute URL
			String absURL = productLinkURL.absUrl("href");
			productsList.add(fetchProductDetails(absURL));
		}
	
		return productsList;
	}

	@Override
	public BigDecimal calculateGrossUnitPrice(List<Product> productList) {
		BigDecimal gross = new BigDecimal(0);
		for (Product p : productList) {
			gross = gross.add(p.getUnitPrice());
		}
		return gross;
	}

	@Override
	public BigDecimal calculateVat(BigDecimal gross) {
		BigDecimal vat = new BigDecimal(0);
		vat = gross.multiply(ProcessProductDetails.VAT_PERCENTAGE).setScale(2, RoundingMode.DOWN);
		return vat;
	}

	@Override
	public void convertToJson(List<Product> productList, Total total) {
		//Create the JsonOutput object
		JsonOutput output = new JsonOutput();
		output.setResults(productList);
		output.setTotal(total);
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		//Finally Prining the JSON output
		System.out.println("JSON OUTPUT : \n"+gson.toJson(output));
		
		
	}
	
}
