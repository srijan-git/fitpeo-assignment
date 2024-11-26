package com.fitpeo.assignment.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.fitpeo.assignment.util.TestUtil;

public class TestBase {
	public static WebDriver driver;
	static Properties props;

	public TestBase() {
		try {
			props = new Properties();

			FileInputStream ipFileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/fitpeo/assignment/config/config.properties");

			props.load(ipFileInputStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = props.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/driver/chromedriver/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.geko.driver",
					System.getProperty("user.dir") + "/driver/gekodriver/geckodriver");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLECIT_WAIT));

		driver.get(props.getProperty("url"));
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot tScreenshot = (TakesScreenshot) driver;
		File sourceFile = tScreenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile,
				new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));

		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}
}
