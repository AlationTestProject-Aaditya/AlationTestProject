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

public class SearchResultsPage extends TestBase {

	private SeleniumUtils seleniumUtils = new SeleniumUtils();

	@FindAll(@FindBy(how = How.CSS, using = ".rush-component span.a-color-state"))
	List<WebElement> searchResultsText;

	@FindBy(css = ".s-inline-block span.a-color-state.a-text-bold")
	WebElement searchResultsTextInline;

	@FindBy(css = "div[data-cel-widget=search_result_0] a .a-text-normal")
	WebElement firstProductNameLink;

	@FindAll(@FindBy(how = How.CSS, using ="div[data-cel-widget=search_result_0] .a-spacing-top-small span[class^=a-price-]"))
	List<WebElement> firstProductPaperbackListPrice;

	@FindAll(@FindBy(how = How.CSS, using = "div[data-cel-widget=search_result_0] .a-color-secondary .a-size-base.a-link-normal"))
	List<WebElement> firstProductAuthorNames;

	/**
	 * @author aaditya.a
	 * Functionality - Constructor to initialize all the elements in the Search Results page
	 */
	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to assert the right Search Results page is displayed with the search text used to search the product
	 * Parameter - Expected Search Text
	 */
	public void assertSearchResultsPage(String expectedSearchText) {
		if (seleniumUtils.elements(searchResultsText)>0){
			Assert.assertTrue(seleniumUtils.getElementText(searchResultsText.get(0)).contains(expectedSearchText));
		}else{
			Assert.assertTrue(seleniumUtils.getElementText(searchResultsTextInline).contains(expectedSearchText));

		}
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to click the first Product Name in the Search results page
	 */
	public void clickFirstProduct() {
		seleniumUtils.click(firstProductNameLink);
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to get the first Product Name from search results page
	 */
	public String getFirstProductNameFromSearchResults() {
		return seleniumUtils.getElementText(firstProductNameLink);
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to get the price of the Paperback edition of the first product in the search results page
	 * Return type - price of the product -- As String
	 */
	public String getFirstProductPaperbackListPrice() {
		StringBuilder listPrice = new StringBuilder();
		for(WebElement price : firstProductPaperbackListPrice){
			listPrice.append(seleniumUtils.getElementText(price));
		}
		return listPrice.toString();
	}

	/**
	 * @author aaditya.a
	 * Functionality - Method to get all the author names of
	 * Return type - Author names as List of Strings

	 */
	public List<String> getFirstProductAuthorNames() {
		List<String> actualAuthorNames = new ArrayList<String>();
		;
		for (WebElement authorName : firstProductAuthorNames) {
			actualAuthorNames.add(authorName.getText());
		}
		return actualAuthorNames;
	}
}
