package com.fitpeo.assignment.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fitpeo.assignment.base.TestBase;

public class RevenuePage extends TestBase {

	Actions actions = new Actions(driver);

	@FindBy(xpath = "//h4[text()='Medicare Eligible Patients']")
	WebElement textToScroll;

	@FindBy(xpath = "//input[@type='range']")
	WebElement inputRangeElement;

	@FindBy(xpath = "//p[normalize-space()='Total Individual Patient/Month']/following-sibling::p")
	WebElement totalIndividualPatientlElement;

	@FindBy(css = "input[type='number']")
	WebElement inputBoxElement;

	@FindBy(xpath = "//div[contains(@class,' css-1eynrej')]//p[contains(@class,'MuiTypography-body1')]")
	List<WebElement> cptCodesElements;

	@FindBy(css = "div[class*='css-1p19z09']")
	WebElement cptCodeElement;

	@FindBy(xpath = "//p[contains(text(),'Per Month:')]//p")
	WebElement reimbursementPerMonthElement;

	public RevenuePage() {
		PageFactory.initElements(driver, this);
	}

	public void scrollDownToSlider() {
		actions.moveToElement(textToScroll).build().perform();
	}

	public void scrollToCptCodes() {
		actions.moveToElement(cptCodeElement).build().perform();
	}

	public void updateInputBoxValue(String totalIndividualPatient) {
		inputBoxElement.click();
		inputBoxElement.sendKeys(Keys.CONTROL + "a");
		inputBoxElement.sendKeys(Keys.BACK_SPACE);
		inputBoxElement.sendKeys(totalIndividualPatient);
	}

	public String getTheRangeValueFromSlider() {
		String value = inputRangeElement.getAttribute("value");
		return value;
	}

	public void selectCPTCodes(List<String> cptCode) {
		cptCodesElements.stream().filter(cptElement -> cptCode.contains(cptElement.getText().trim()))
				.forEach(cptElement -> {
					WebElement checkbox = cptElement.findElement(By.xpath(".//following-sibling::label/span/input"));
					checkbox.click();
				});
	}

	public String getReimbursementPerMonthElement() {
		return reimbursementPerMonthElement.getText();
	}

	public String verifyRevenuePageUrl() {
		return driver.getCurrentUrl();
	}
}
