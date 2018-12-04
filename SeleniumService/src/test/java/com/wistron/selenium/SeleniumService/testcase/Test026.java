package com.wistron.selenium.SeleniumService.testcase;

import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wistron.selenium.SeleniumService.Constant;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Test026 extends TestCase {

	public static boolean REMOTE_MODE = true;

	protected WebDriver driver;

	public void waitForPage(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	@Before
	public void setUp() throws Exception {
		if (REMOTE_MODE)
			driver = new RemoteWebDriver(new URL("http://192.168.31.117:4444/wd/hub"), DesiredCapabilities.chrome());
		else {
			System.setProperty("webdriver.chrome.driver", "target/test-classes/chromedriver");
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test026() throws Exception {
		try {
			driver.get(Constant.BOOM_URL);
			if (!driver.getCurrentUrl().startsWith(Constant.BOOM_URL)) {
				JavascriptExecutor javascriptRunner = ((JavascriptExecutor) driver);
				javascriptRunner
						.executeScript("$('#loginId').attr('name','j_username').val('" + Constant.SUPER_USERID + "');");
				javascriptRunner.executeScript(
						"$('#password').attr('name','j_password').val('" + UUID.randomUUID().toString() + "');");
				Thread.sleep(1000);
				javascriptRunner.executeScript("document.loginForm.submit();");
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				int size = driver.findElements(By.cssSelector("#teacherCoursePackageRoot")).size();
				Assert.assertTrue(size == 0);
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			Boolean Result = false;
			Assert.assertTrue(Result);
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
