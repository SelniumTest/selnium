package com.wistron.selenium.SeleniumService;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SeleniumTest {
	private static final String WORKING_DIR = SeleniumTest.class.getClassLoader().getResource("").toString();
	private static final String WICC_URL = "http://192.168.21.35:8080/wicc";
	private static final String STAT_URL = "http://192.168.21.35:8080/wicc/cooc/infoStatistic.jsp";
	private static final String MNG_URL = "http://192.168.21.35:8080/wicc/cooc/knowledgeMap.jsp";
	private static final String USERID = "superadmin";
	private static final String PWD = "123456q";
	private static final String OUTLINE_IMPORT = WORKING_DIR + "import\\Outline_Grade1-9_Example.xlsx";

	private WebDriver driver;
	static String timeStamp = new SimpleDateFormat("yyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	public static final String resourceName = "AutoTest" + timeStamp;
	private List<Pair<String, Boolean>> Report = new ArrayList<Pair<String, Boolean>>();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "target\\test-classes\\bin\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(WICC_URL);
		if (!driver.getCurrentUrl().startsWith(WICC_URL)) {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor javascriptRunner = ((JavascriptExecutor) driver);
				javascriptRunner.executeScript("$('#loginId').attr('name','j_username').val('" + USERID + "');");
				javascriptRunner.executeScript("$('#password').attr('name','j_password').val('" + PWD + "');");
				javascriptRunner.executeScript("document.loginForm.submit();");
			}
			if (driver.findElement(By.xpath(".//input[@name='authorize']")).isDisplayed())
				driver.findElement(By.xpath(".//input[@name='authorize']")).click();
		}
		// Pair<String,Boolean> p=Pair.of("", right);

	}

	@Test
	public void testInsert() throws Exception {
		driver.get(WICC_URL);
		try {
			// insert a High School resource
			driver.findElement(By.xpath(".//button[contains(@onclick, 'loadUpload')]")).click();
			driver.findElement(By.xpath(".//input[@value='30']")).click();
			// choose subject first to wait ajax loading data
			driver.findElement(By.xpath("//select[@id='subjectCode']/option[@value='14']")).click();
			driver.findElement(By.id("resourceNameInput")).sendKeys(resourceName);
			driver.findElement(By.id("resourceIndrTextarea")).sendKeys(resourceName);
			driver.findElement(By.xpath(".//input[@value='14']")).click();
			driver.findElement(By.xpath("//select[@id='subjectCode']/option[@value='14']")).click();
			driver.findElement(By.xpath("//select[@id='publisher']/option[@value='VER501']")).click();
			driver.findElement(By.xpath("//select[@id='semester']/option[@value='57721']")).click();
			driver.findElement(By.xpath("//select[@id='chapterSection']/option[@value='57722']")).click();
			driver.findElement(By.xpath("//select[@id='resType']/option[@value='resource_012']")).click();

			// Select selectChapter = new
			// Select(driver.findElement(By.id("chapterSection")));
			// selectChapter.selectByValue("56782");
			driver.findElement(By.xpath(".//input[@id='label01']")).click();
			driver.findElement(By.id("saveresource")).click();

			// wait until reload page
			waitPage("p8Btn");
			// search for inserted resource, should be found
			driver.findElement(By.id("geren")).click();
			driver.findElement(By.id("search")).sendKeys(resourceName);
			driver.findElement(By.xpath(".//input[contains(@onclick, 'searchContent')]")).click();
			String results = driver.findElement(By.className("cnpop")).getText();
			Boolean Result = results.equals(resourceName) ? true : false;
			if (Result) {
				Assert.assertTrue(Result);
			} else {
				Assert.assertFalse(Result);
			}
			Report.add(Pair.of("testInsert", Result));
		} catch (Exception e) {
			Boolean Result = false;
			Report.add(Pair.of("testInsert", Result));
			Assert.assertFalse(Result);
			e.printStackTrace();
		}

	}

	@Test
	public void testStatistic() throws Exception {
		try {
			driver.get(STAT_URL);
			Boolean Result;
			if (driver.findElement(By.id("table1")).isDisplayed()) {
				Result = true;
				Assert.assertTrue(Result);
			} else {
				Result = false;
				Assert.assertFalse(Result);
			}
			Report.add(Pair.of("testStatistic", Result));
		} catch (Exception e) {
			Boolean Result = false;
			Report.add(Pair.of("testStatistic", Result));
			e.printStackTrace();
		}
	}

	@Test
	public void testUpload() throws Exception {
		try {
			driver.get(MNG_URL);
			Boolean Result = null;
			if (driver.findElement(By.id("gradeCode")).isDisplayed()) {
				driver.findElement(By.id("importXml")).click();
				// select 9年1貫&數學
				driver.findElement(By.xpath("//select[@id='gradeCode']/option[@value='1']")).click();
				driver.findElement(By.xpath("//select[@id='knowledgeSubjectAll']/option[@value='14']")).click();
				driver.findElement(By.id("uploadExcel")).click();
				System.out.println(OUTLINE_IMPORT);
				setClipboardData(OUTLINE_IMPORT);

				// native key strokes for CTRL, V and ENTER keys
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				waitPage("fileText");
				driver.findElement(By.id("uploadData")).click();
				waitPage("aui_content");

				String webString = driver.findElement(By.xpath("//div[contains(@class, 'aui_content')]")).getText();
				Result = "教綱匯入成功".equals(webString) ? true : false;
				driver.findElement(By.className("aui_state_highlight")).click();
				if (Result) {
					Assert.assertTrue(Result);
				} else {
					Assert.assertFalse(Result);
				}
				Report.add(Pair.of("testUpload", Result));

			}
		} catch (Exception e) {
			Boolean Result = false;
			Assert.assertFalse(Result);
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() throws Exception {
		try {
			driver.get(WICC_URL);
			driver.findElement(By.id("geren")).click();
			driver.findElement(By.id("search")).sendKeys(resourceName);
			waitPage("p8Btn");
			driver.findElement(By.xpath(".//input[@name='selectOne']")).click();
			driver.findElement(By.xpath(".//button[contains(@onclick, 'deleteSelected')]")).click();
			// driver.findElement(By.xpath("//*[contains(@class,
			// 'aui_state_highlight')]")).click();
			driver.findElement(By.className("aui_state_highlight")).click();
			driver.findElement(By.xpath(".//input[contains(@onclick, 'searchContent')]"));

			String results = driver.findElement(By.className("p8Btn")).getText();
			Boolean Result = "".equals(results) ? true : false;
			if (Result) {
				Assert.assertTrue(Result);
			} else {
				Assert.assertFalse(Result);
			}
			Report.add(Pair.of("testDelete", Result));

		} catch (Exception e) {
			Boolean Result = false;
			Assert.assertFalse(Result);
			Report.add(Pair.of("testDelete", Result));
			e.printStackTrace();
		}

	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public void waitPage(String className) {
		WebElement elem = driver.findElement(By.className(className));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	public void genCSVUtil(List<Pair<String, Boolean>> List) throws Exception {

		String csvFile = "target\\test-classes\\report\\" + "SeleniumTestReport" + timeStamp + ".csv";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));
			// FileWriter writer = new FileWriter(csvFile);
			StringBuilder sb = new StringBuilder();
			Iterator<Pair<String, Boolean>> it = List.iterator();
			while (it.hasNext()) {
				sb.append(it.next());
				writer.write(sb.toString());
				writer.newLine();
			}
			writer.close();
		} catch (Exception e) {
			throw new FileNotFoundException();
		}

	}

	@After
	public void tearDown() throws Exception {
		try {
			genCSVUtil(Report);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		driver.quit();
	}

}
