package com.wistron.selenium.SeleniumService.testcase;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import com.wistron.selenium.SeleniumService.Constant;

import junit.framework.Assert;

public class Test009 extends BaseTest {

	@Test
	public void test009() throws Exception {
		try {
			waitForPage(driver.findElement(By.cssSelector("#teacherCoursePackageRoot")));
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(By.cssSelector("#teacherCoursePackageRoot > a")))
					.click(driver.findElement(By.cssSelector("#teacherQuestionList > a"))).build().perform();
			Thread.sleep(2000);

			JavascriptExecutor jse = (JavascriptExecutor) driver;

			// Search by title and question type after edit
			searchQuestion(driver, Constant.BLANK_FILL_QUESTION, "QuestionTest");
			jse.executeScript("window.scrollBy(0,\"650\")");
			Thread.sleep(1000);

			// Copy question by question type
			copyQuestion(driver, Constant.BLANK_FILL_QUESTION, jse);

			// Search by title and question type
			searchQuestion(driver, Constant.BLANK_FILL_QUESTION, "QuestionTest");
			jse.executeScript("window.scrollBy(0,\"650\")");

			// catch first data
			String webString = driver
					.findElement(By.cssSelector(
							"#showlist > div.news_content > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2) > p"))
					.getText();
			Assert.assertTrue(webString.contains("Copy"));
			Thread.sleep(3000);

			// Delete Copy question
			deleteQuestion(driver, jse);

			// catch first data to verify after delete action
			webString = driver
					.findElement(By.cssSelector(
							"#showlist > div.news_content > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2) > p"))
					.getText();
			Assert.assertFalse(webString.contains("Copy"));
			Thread.sleep(3000);
		} catch (Exception e) {
			Boolean Result = false;
			Assert.assertTrue(Result);
			e.printStackTrace();
		}
	}

}
