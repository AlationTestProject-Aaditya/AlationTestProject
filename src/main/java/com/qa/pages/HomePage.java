package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.utils.SeleniumUtils;

public class HomePage extends TestBase{
	@FindBy(id = "searchDropdownBox")
	WebElement searchDropdownList;
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searchTextBox;
	
	@FindBy(css=".nav-input[value=Go]")
	WebElement searchButton;
	

	/**
	 * @author aaditya.a
	 * Functionality - Constructor to Initialize all the elements in Home Page
	 */
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	SeleniumUtils seleniumUtils = new SeleniumUtils();

	/**
	 * @author aaditya.a
	 * Functionality - Assert the Home page title
	 * Parameters - Expected Page Title 
	 */
	public void assertPageTitle(String expectedPageTitle) {
		Assert.assertEquals(driver.getTitle(), expectedPageTitle);
	}

	/**
	 * @author aaditya.a
	 * Functionality - Select desired dropdown value from Search Dropdown list
	 * Parameters - Value to select in the dropdown
	 */
	public void selectSearchDropdownList(String selectValue){
		seleniumUtils.selectFromDropdownList(searchDropdownList, selectValue);
	}
	
	/**
	 * @author aaditya.a
	 * Functionality - Enter text in the Search text box
	 * Parameters - Text to Enter
	 */

	public void enterSearchText(String textToEnter) {
		
		
		seleniumUtils.enterText(searchTextBox, textToEnter);
	}

	/**
	 * @author aaditya.a
	 * Functionality - Click Search button
	 */
	public void clickSearchButton() {
		seleniumUtils.click(searchButton);
	}
	
}
