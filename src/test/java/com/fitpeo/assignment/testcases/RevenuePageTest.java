package com.fitpeo.assignment.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fitpeo.assignment.base.TestBase;
import com.fitpeo.assignment.pages.HomePage;
import com.fitpeo.assignment.pages.RevenuePage;
import com.fitpeo.assignment.util.TestUtil;

public class RevenuePageTest extends TestBase {
	HomePage homePage;
	RevenuePage revenuePage;

	public RevenuePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		homePage = new HomePage();
		revenuePage = homePage.moveToRevenueCalculator();
	}

	@Test(priority = 1)
	public void validate_slider_range_value_By_updating_textbox_value_test() {
		revenuePage.scrollDownToSlider();
		revenuePage.updateInputBoxValue("560");
		String actualValue = revenuePage.getTheRangeValueFromSlider();
		Assert.assertEquals(actualValue, "560");
	}

	@Test(priority = 2)
	public void verify_text_field_by_adjusting_slider_test() {
		revenuePage.scrollDownToSlider();
		String actualTextFieldValue = revenuePage.adjustSliderRange();
		Assert.assertEquals(actualTextFieldValue, "820");
	}

	@Test(priority = 3, dataProvider = "jsonData")
	public void validate_total_reimbursement_by_selecting_cptcode_test(List<String> cptCodes, List<String> rangeValue,
			String reimbursementMonth) {
		revenuePage.scrollDownToSlider();
		revenuePage.updateInputBoxValue(rangeValue.get(1));
		revenuePage.scrollToCptCodes();
		revenuePage.selectCPTCodes(cptCodes);
		String totalReimbursementPerMonthElement = revenuePage.getReimbursementPerMonthElement();
		Assert.assertEquals(totalReimbursementPerMonthElement, reimbursementMonth);
	}

	@DataProvider(name = "jsonData")
	public Object[][] getJsonData() throws IOException {
		List<HashMap<String, Object>> jsonData = TestUtil.getJsonData(
				System.getProperty("user.dir") + "/src/main/java/com/fitpeo/assignment/testdata/JsonDataProvider.json");

		Object[][] data = new Object[jsonData.size()][3];
		for (int i = 0; i < jsonData.size(); i++) {
			HashMap<String, Object> entry = jsonData.get(i);
			List<String> cptCodes = (List<String>) entry.get("cptCodes");
			List<String> rangeValue = (List<String>) entry.get("range");
			String reimbursementMonth = (String) entry.get("reimbursementMonth");

			data[i][0] = cptCodes;
			data[i][1] = rangeValue;
			data[i][2] = reimbursementMonth;
		}
		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
