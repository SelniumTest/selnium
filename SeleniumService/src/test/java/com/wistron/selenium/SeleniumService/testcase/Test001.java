package com.wistron.selenium.SeleniumService.testcase;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import com.wistron.selenium.SeleniumService.Constant;

import junit.framework.Assert;

public class Test001 extends BaseTest {

	@Test
	public void test001() throws Exception {
		try {
			waitForPage(driver.findElement(By.cssSelector("#M000000169")));
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(By.cssSelector("#M000000169 > a"))).click(driver.findElement(By.cssSelector("#M000000169 > ul > li:nth-child(1) > a"))).build()
					.perform();
			Thread.sleep(2000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;

			// Add question by question type
			saveAddQuestion(driver, Constant.CHOICE_QUESTION, jse);

			// Search by title and question type
			searchQuestion(driver, Constant.CHOICE_QUESTION, "QuestionTest");
			jse.executeScript("window.scrollBy(0,\"650\")");

			// catch first data
			String webString = driver.findElement(
					By.cssSelector("#showlist > div.news_content > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2) > p"))
					.getText();
			Assert.assertTrue(webString.contains("Add"));
			Thread.sleep(3000);
		} catch (Exception e) {
			Boolean Result = false;
			Assert.assertTrue(Result);
			e.printStackTrace();
		}
	}

}
