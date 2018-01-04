package com.wistron.selenium.SeleniumService.testcase;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import com.wistron.selenium.SeleniumService.Constant;

import junit.framework.TestCase;

public class BaseTest extends TestCase {
	public static boolean REMOTE_MODE = true;

	protected WebDriver driver;

	public BaseTest() {

	}

	public BaseTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {

		if (REMOTE_MODE)
			driver = new RemoteWebDriver(new URL("http://192.168.21.132:4444/wd/hub"), DesiredCapabilities.chrome());
		else {
			System.setProperty("webdriver.chrome.driver", "target/test-classes/chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(Constant.WIAccessControl_URL);
		if (!driver.getCurrentUrl().equals(Constant.WIAccessControl_URL)) {
			driver.findElement(By.xpath(
					"//*[@id=\"username\"]"))
					.sendKeys(Constant.SUPER_USERID);
			driver.findElement(By.xpath(
					"//*[@id=\"password\"]"))
					.sendKeys(Constant.PWD);
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"loginbtn\"]"))
					.click();
		}

	}

	public void waitForPage(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	public void waitPage(String className) {
		WebElement elem = driver.findElement(By.className(className));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
