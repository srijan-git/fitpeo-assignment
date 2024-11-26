package com.fitpeo.assignment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fitpeo.assignment.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(css = "img[alt='FitPeo']")
	WebElement fitpeoImage;

	@FindBy(xpath = "//div[text()='Revenue Calculator']/parent::a")
	WebElement revenueCalculatorLink;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String validateHomePageTitle() {
		return driver.getTitle();
	}

	public boolean validateFitpeoLogo() {
		return fitpeoImage.isDisplayed();
	}

	public RevenuePage moveToRevenueCalculator() {
		revenueCalculatorLink.click();
		return new RevenuePage();
	}
}
