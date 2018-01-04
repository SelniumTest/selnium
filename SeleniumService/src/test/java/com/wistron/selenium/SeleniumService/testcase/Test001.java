package com.wistron.selenium.SeleniumService.testcase;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.wistron.selenium.SeleniumService.Constant;

import junit.framework.Assert;

public class Test001 extends BaseTest {
	@Test
	public void test001() throws Exception {
		try{
			Thread.sleep(3000);
			driver.get(Constant.PROJECT_SETUP);
			Thread.sleep(1000);
	        Boolean Result = null;
			driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[2]/div/div[4]/div[2]/div/button")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/input"))
					.sendKeys("DollyTest");
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[3]/div/input"))
					.sendKeys("192.192.192.192");
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[1]/input")).sendKeys("192.168.1.1");
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[2]/input")).sendKeys("11");
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[3]/input")).sendKeys("1");
			Thread.sleep(1000);
	        driver.findElement(
			By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[1]/input")).sendKeys("192.168.1.1");
            driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[2]/input")).sendKeys("11");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[3]/input")).sendKeys("1");
			Thread.sleep(1000);
			driver.findElement(
			By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[1]/input")).sendKeys("192.168.1.1");
            driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[2]/input")).sendKeys("11");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[3]/input")).sendKeys("1");
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[12]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[12]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[13]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[14]/div/input")).click();		
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[15]/div/input")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[16]/div/input")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[3]/button[1]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/input")).sendKeys("DollyTest");
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[3]/div/input")).sendKeys("192.168.1.2");
		    driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[4]/div/input")).sendKeys("AA:BB:AA:AA:AA:AA");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[5]/div[1]/input")).sendKeys("250");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[6]/div[1]/input")).sendKeys("250");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[6]/div[3]/button")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[3]/button[1]")).click();
	        Thread.sleep(3500);
	        String webString = driver
					.findElement(By
							.xpath("//*[@id=\"app\"]/div/div[3]/div[2]/div/div[1]/div"))
					.getText();
	        Thread.sleep(2000);
	        Result = "新增裝置成功".equals(webString) ? true : false;
			Assert.assertTrue(Result);
		}
		catch (Exception e) {
			Boolean Result = false;
			Assert.assertTrue(Result);
			 e.printStackTrace();
		}
	}
	
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

}
