package com.qa.utils;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SeleniumUtils extends TestBase {

	public void selectFromDropdownList(WebElement elementToSelect, String visibleText) {
		Select select = new Select(elementToSelect);
		select.selectByVisibleText(visibleText);
	}

	public void enterText(WebElement elementToEnter, String textToEnter) {
		elementToEnter.sendKeys(textToEnter);
	}

	public void click(WebElement elementToClick) {
		elementToClick.click();
	}

	public void assertElementText(WebElement actualElement, String expectedValue) {
		Assert.assertEquals(actualElement.getText(), expectedValue);
	}

	public String getElementText(WebElement elementToGetText) {
		return elementToGetText.getText();
	}

	public int elements(List<WebElement> elements) {
		return elements.size();
	}
}
