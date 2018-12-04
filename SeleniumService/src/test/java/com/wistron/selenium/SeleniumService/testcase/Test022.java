package com.wistron.selenium.SeleniumService.testcase;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import com.wistron.selenium.SeleniumService.Constant;

import junit.framework.Assert;

public class Test022 extends BaseTest {

	@Test
	public void test022() throws Exception {
		try {
			waitForPage(driver.findElement(By.cssSelector("#teacherCoursePackageRoot")));
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(By.cssSelector("#teacherCoursePackageRoot > a"))).click(driver.findElement(By.cssSelector("#teacherQuestionList > a"))).build()
					.perform();
			Thread.sleep(2000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;

			// Add question by question type
			saveAddQuestion(driver, Constant.MATCH_QUESTION, jse);

			// Search by title and question type
			searchQuestion(driver, Constant.MATCH_QUESTION, "QuestionTest");
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
