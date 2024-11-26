package com.fitpeo.assignment.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fitpeo.assignment.base.TestBase;
import com.fitpeo.assignment.pages.HomePage;
import com.fitpeo.assignment.pages.RevenuePage;

public class HomePageTest extends TestBase {
	HomePage homePage;
	RevenuePage revenuePage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		homePage = new HomePage();
	}

	@Test(priority = 1)
	public void navigate_to_fitpeo_homepage_test() {
		String pageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(pageTitle, "Remote Patient Monitoring (RPM) - fitpeo.com");
	}

	@Test(priority = 2)
	public void navigate_to_revenue_calculator_page_test() {
		revenuePage = homePage.moveToRevenueCalculator();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
