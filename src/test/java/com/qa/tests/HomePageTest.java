package com.qa.tests;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.ProductPage;
import com.qa.pages.SearchResultsPage;
import com.qa.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;



public class HomePageTest extends TestBase {
	
	HomePage homePage;
	SearchResultsPage searchResultsPage;
	ProductPage productPage;
	
	static Logger log = Logger.getLogger(HomePageTest.class);
	
	public HomePageTest(){
		super();
		
	}

	@BeforeMethod
	public void setUp(){
		initDriver();
		homePage= new HomePage();
		searchResultsPage = new SearchResultsPage();
		productPage = new ProductPage();
	}
	
	/**
	 * @author aaditya.a
	 * Functionality - To verify the user is able to search for Books in the application
	 */
	@Test
	public void verifyBooksSearchTest(){
		homePage.assertPageTitle("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
		homePage.selectSearchDropdownList("Books");
		homePage.enterSearchText("data catalog");
		homePage.clickSearchButton();
		searchResultsPage.assertSearchResultsPage("data catalog");
		String productName = searchResultsPage.getFirstProductNameFromSearchResults();
		String productPrice = searchResultsPage.getFirstProductPaperbackListPrice();
		List<String> firstProductAuthorNames = searchResultsPage.getFirstProductAuthorNames();
		searchResultsPage.clickFirstProduct();
		productPage.assertProductPageTitle(productName);
		productPage.assertProductName(productName);
		productPage.assertProductAuthorNames(firstProductAuthorNames);
		productPage.assertProductPrice(productPrice);
	}
	

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		extentTest = extent.startTest(result.getName());
		if(result.getStatus()==result.FAILURE){
		
			log.info(result.getName());
			extentTest.log(LogStatus.FAIL, result.getName()+" failed"+result.getThrowable());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(TestUtils.getScreenshot(result.getName())));
		}else{
			extentTest.log(LogStatus.PASS, result.getName()+" passed");
		}
		extent.endTest(extentTest);
		
		driver.quit();
	}
	
}
