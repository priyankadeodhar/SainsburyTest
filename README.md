# SainsburyTest
This program will get the products details from a given sainsbury webpage
Its written in Java 8
Using Jsoup API(version 1.11.3) it firstly reads the HTML document using the Test link provided "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html"
If success then browses through each product link and creates Product object by parsing each product link 
Once all the products are parsed , it calculates gross and vat value if successful in creating the Product list
Finally using Gson (version 2.8.2) , it creates the Json output.
