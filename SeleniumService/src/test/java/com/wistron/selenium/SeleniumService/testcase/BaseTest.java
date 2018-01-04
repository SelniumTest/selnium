package com.wistron.selenium.SeleniumService.testcase;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		driver.get(Constant.CLASS_URL);
		String currentDns = getCurrentDns(driver.getCurrentUrl());
		if (!driver.getCurrentUrl().startsWith(Constant.CLASS_URL)) {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor javascriptRunner = ((JavascriptExecutor) driver);
				javascriptRunner.executeScript("$('#loginId').attr('name','j_username').val('" + Constant.SUPER_USERID + "');");
				javascriptRunner.executeScript("$('#password').attr('name','j_password').val('" + Constant.PWD + "');");
				javascriptRunner.executeScript("document.loginForm.submit();");
			}
			if (currentDns.equals(getCurrentDns(driver.getCurrentUrl()))) {
				if (null != driver.findElement(By.xpath(".//input[@name='authorize']")) && driver.findElement(By.xpath(".//input[@name='authorize']")).isDisplayed()) {
					driver.findElement(By.xpath(".//input[@name='authorize']")).click();
				}
			}
		}
	}

	public void waitForPage(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	private String getCurrentDns(String url) {
		Pattern p = Pattern.compile("http\\:\\/\\/(.*?)\\/");
		Matcher m = p.matcher(url);
		if (m.find()) {
			return m.group(1);
		}
		return url;
	}

	public boolean getPageCount() throws InterruptedException {
		int lastPage = Integer.parseInt(driver.findElement(By.cssSelector("#appKeyCountlist > span.pagelinks > strong")).getText());
		int lastPageCount = driver.findElements(By.cssSelector("#contentFileName > tbody > tr")).size();
		int pageCount = (lastPage - 1) * 10 + lastPageCount;

		boolean countResult = false;
		driver.findElement(By.cssSelector("#appKeyCountlist > span.pagelinks > a:last-child")).click();
		Thread.sleep(2000);

		String totalCount = driver.findElement(By.cssSelector("#appKeyCountlist > span.pagebanner")).getText();

		if (totalCount.compareTo(String.valueOf(pageCount)) > 0)
			countResult = true;

		return countResult;
	}

	public String getSize(String resultString) {
		return resultString.substring(resultString.indexOf(":") + 1, resultString.indexOf("G") - 1).replace(" ", "");
	}

	public void searchQuestion(WebDriver driver, String type, String title) {
		waitForPage(driver.findElement(By.cssSelector("#advancedBtn")));
		driver.findElement(By.cssSelector("#advancedBtn")).click();
		waitForPage(driver.findElement(By.cssSelector(".searchT")));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("$(\"#questionTypeId option[value='" + type + "']\").prop(\"selected\", true)");
		driver.findElement(By.cssSelector("#keywords")).clear();
		driver.findElement(By.cssSelector("#keywords")).sendKeys(title);
		jse.executeScript("goQuery()");
		driver.findElement(By.cssSelector("#keywords")).clear();
	}

	public void saveAddQuestion(WebDriver driver, String type, JavascriptExecutor jse) throws InterruptedException {
		waitForPage(driver.findElement(By.className("container")));
		jse.executeScript("goAdd()");
		waitForPage(driver.findElement(By.id("editFrame")));
		driver.switchTo().frame(driver.findElement(By.id("editFrame")));
		waitForPage(driver.findElement(By.id("cke_questionContent")));
		waitForPage(driver.findElement(By.id("cke_answerAnalyse")));

		jse.executeScript("$('#school option').eq(1).prop('selected', true)"); // 學校
		jse.executeScript("$('#school').trigger('change')");
		Thread.sleep(1000);
		jse.executeScript("$('#subjectId option').eq(1).prop('selected', true)"); // 科目
		Thread.sleep(1000);
		jse.executeScript("$(\".searchT\").find(\"select option[value='" + type + "']\").prop(\"selected\", true).trigger(\"change\")"); // 選擇題型
		switchAndScroll(driver, jse, 500);
		Thread.sleep(1000);
		jse.executeScript("$(\".searchT\").find(\"select option[value=aut_003]\").prop(\"selected\", true).trigger(\"change\")"); // 授權模式
		jse.executeScript("$(\"#areaTypeUL > li:nth-child(3)\").click()"); // 公開狀態
		jse.executeScript("$(\"#diffcultUL > li:nth-child(2)\").click()"); // 難易度
		Thread.sleep(1000);
		jse.executeScript("$(\"#cke_questionContent iframe\").contents().find(\"html body\").html(\"QuestionTest Add_" + type + "\")"); // 題目
		if (type.equals(Constant.CHOICE_QUESTION) || type.equals(Constant.SELECTION_QUESTION) || type.equals(Constant.MATCH_QUESTION)) {
			addOptionByType(driver, type, jse);
		} else if (type.equals(Constant.TRUE_FALSE_QUESTION)) {
			jse.executeScript("$(\"#singleAnswerD > input:nth-child(1)\").prop(\"checked\", true)");
			jse.executeScript("$('input[name=\"question.answer\"]').val(\"O\")");
		} else if (type.equals(Constant.SHORT_ANS_QUESTION) || type.equals(Constant.BLANK_FILL_QUESTION) || type.equals(Constant.ESSAY_QUESTION)
				|| type.equals(Constant.MATH_QUESTION)) {
			jse.executeScript("$(\"#cke_answer_Content iframe\").contents().find(\"html body\").html(\"Answer_" + type + "\")");
		}
		jse.executeScript("goSave()");
	}

	public void addOptionByType(WebDriver driver, String type, JavascriptExecutor jse) throws InterruptedException {
		String searchElement = (type.equals(Constant.MATCH_QUESTION) ? "#matchTable > tbody > tr > td > .ckeditor" : "#question_option > .ckeditor");
		String queContent = (type.equals(Constant.MATCH_QUESTION) ? "m_queContent" : "question_optionContent");
		int size = driver.findElements(By.cssSelector(searchElement)).size();
		size = (type.equals(Constant.MATCH_QUESTION) ? size = size / 2 : size);
		for (int i = 0; i < size; i++) {
			switchAndScroll(driver, jse, 250);
			jse.executeScript("$(\"#cke_" + queContent + "_" + i + " iframe\").contents().find(\"html body\").html(\"Answer_" + i + "\")");
			Thread.sleep(1000);
			if (type.equals(Constant.MATCH_QUESTION)) {
				jse.executeScript("$(\"#cke_m_ansContent_" + i + " iframe\").contents().find(\"html body\").html(\"Match_Answer_" + i + "\")");
			}
		}

		// 選擇正確答案
		if (type.equals(Constant.CHOICE_QUESTION) || type.equals(Constant.SELECTION_QUESTION)) {
			jse.executeScript("$(\"#question_option\").find(\"a[type=radio]\").eq(0).click()");
		}
	}

	public void editQuestion(WebDriver driver, String type, JavascriptExecutor jse) throws InterruptedException {
		waitForPage(driver.findElement(By.className("container")));
		jse.executeScript("$(\"#showlist\").find(\".btn_edit\").eq(0).click()");
		waitForPage(driver.findElement(By.id("editFrame")));
		driver.switchTo().frame(driver.findElement(By.id("editFrame")));
		waitForPage(driver.findElement(By.id("cke_questionContent")));
		waitForPage(driver.findElement(By.id("cke_answerAnalyse")));

		jse.executeScript("$('#school option').eq(1).prop('selected', true)"); // 學校
		jse.executeScript("$('#school').trigger('change')");
		Thread.sleep(1000);
		jse.executeScript("$('#subjectId option').eq(2).prop('selected', true)"); // 科目
		Thread.sleep(1000);
		switchAndScroll(driver, jse, 500);
		Thread.sleep(1000);
		jse.executeScript("$(\".searchT\").find(\"select option[value=aut_001]\").prop(\"selected\", true).trigger(\"change\")"); // 授權模式
		jse.executeScript("$(\"#areaTypeUL > li:nth-child(1)\").click()"); // 公開狀態
		jse.executeScript("$(\"#diffcultUL > li:nth-child(1)\").click()"); // 難易度
		jse.executeScript("$(\"#cke_questionContent iframe\").contents().find(\"html body\").empty()"); // reset content
		Thread.sleep(1000);
		jse.executeScript("$(\"#cke_questionContent iframe\").contents().find(\"html body\").html(\"QuestionTest Edit_" + type + "\")"); // fill content
		Thread.sleep(1000);
		jse.executeScript("goSave()");
	}

	public void copyQuestion(WebDriver driver, String type, JavascriptExecutor jse) throws InterruptedException {
		waitForPage(driver.findElement(By.className("container")));
		jse.executeScript("$(\"#showlist\").find(\".btn_copy\").eq(0).click()");
		waitForPage(driver.findElement(By.id("editFrame")));
		driver.switchTo().frame(driver.findElement(By.id("editFrame")));
		waitForPage(driver.findElement(By.id("cke_questionContent")));
		waitForPage(driver.findElement(By.id("cke_answerAnalyse")));

		jse.executeScript("$('#school option').eq(1).prop('selected', true)"); // 學校
		jse.executeScript("$('#school').trigger('change')");
		Thread.sleep(1000);
		jse.executeScript("$('#subjectId option').eq(1).prop('selected', true)"); // 科目
		Thread.sleep(1000);
		switchAndScroll(driver, jse, 500);
		Thread.sleep(1000);
		jse.executeScript("$(\".searchT\").find(\"select option[value=aut_002]\").prop(\"selected\", true).trigger(\"change\")"); // 授權模式
		jse.executeScript("$(\"#areaTypeUL > li:nth-child(2)\").click()"); // 公開狀態
		jse.executeScript("$(\"#diffcultUL > li:nth-child(2)\").click()"); // 難易度
		jse.executeScript("$(\"#cke_questionContent iframe\").contents().find(\"html body\").empty()"); // reset content
		Thread.sleep(1000);
		jse.executeScript("$(\"#cke_questionContent iframe\").contents().find(\"html body\").html(\"QuestionTest Copy_" + type + "\")"); // fill content
		Thread.sleep(1000);
		jse.executeScript("goSave()");
	}

	public void deleteQuestion(WebDriver driver, JavascriptExecutor jse) throws InterruptedException {
		jse.executeScript("$(\"#showlist\").find(\".btn_del\").eq(0).click()");
		Thread.sleep(1000);
		waitForPage(driver.findElement(By.className("aui_state_focus")));
		jse.executeScript("$(\".aui_state_focus\").find(\".aui_state_highlight\").click()");
		Thread.sleep(1000);

	}

	public void switchAndScroll(WebDriver driver, JavascriptExecutor jse, int value) {
		driver.switchTo().defaultContent();
		jse.executeScript("window.scrollBy(0," + value + ")");
		driver.switchTo().frame(driver.findElement(By.id("editFrame")));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
