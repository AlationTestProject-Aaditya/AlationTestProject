package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.utils.SeleniumUtils;

public class ProductPage extends TestBase{

	
	private SeleniumUtils seleniumUtils = new SeleniumUtils();
	
	@FindBy(id = "productTitle")
	WebElement productName;
	
	@FindAll(@FindBy(how = How.CSS, using = ".contributorNameID"))
	List<WebElement> authorNames;

	@FindBy(css = "#newOfferAccordionRow .header-price")
	WebElement productPrice;

	/**
	 * @author aaditya.a
	 * Functionality - Constructor to initialize all the elements in the Product page
	 */
	public ProductPage(){
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @author aaditya.a
	 * Functionality - Method to assert Product Page Title
	 * Parameter - Expected Page Title text
	 */
	
	public void assertProductPageTitle(String expectedPageTitle) {
		Assert.assertTrue(driver.getTitle().contains(expectedPageTitle));
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to assert Product Name
	 * Parameter - Expected Product Name text
	 */
	
	public void assertProductName(String expectedProductName) {
		seleniumUtils.assertElementText(productName, expectedProductName);
		
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to assert name of the authors of the book
	 * Parameter - Expected List of Author names
	 */
	public void assertProductAuthorNames(List<String> firstProductAuthorNames) {
		List<String> actualProductPageAuthorNames = new ArrayList<String>();
		for(WebElement authorName : authorNames){
			actualProductPageAuthorNames.add(authorName.getText());
		}
		Assert.assertTrue(actualProductPageAuthorNames.containsAll(firstProductAuthorNames));
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to assert Product Price
	 * Parameter - Expected Product Price
	 */
	public void assertProductPrice(String expectedProductPrice) {
		String productListPrice = seleniumUtils.getElementText(productPrice).replace(".", "");
		Assert.assertEquals(productListPrice, expectedProductPrice);
	}

}
