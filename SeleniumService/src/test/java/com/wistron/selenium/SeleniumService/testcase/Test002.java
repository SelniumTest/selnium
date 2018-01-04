package com.wistron.selenium.SeleniumService.testcase;

import org.junit.Test;
import org.openqa.selenium.By;

import com.wistron.selenium.SeleniumService.Constant;
import com.wistron.selenium.SeleniumService.SeleniumTest;

import junit.framework.Assert;

public class Test002 extends BaseTest {

	@Test
	public void test002() throws Exception {
		try {
			Thread.sleep(3000);
			driver.get(Constant.PROJECT_SETUP);
			Thread.sleep(1000);
			Boolean Result = null;
			driver.findElement(
					By.xpath("//*[@id=\"app\"]/div/div[3]/div[2]/div/table/tbody/tr[1]/td[4]/button[1]"))
					.click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/input")).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/input"))
			.sendKeys("DollyTest1");
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[3]/div/input")).clear();
			Thread.sleep(1000);
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[3]/div/input"))
			.sendKeys("192.192.192.199");
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[4]/div/button[1]")).click();
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[5]/div/button[2]")).click();
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[6]/div/button[1]")).click();
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[7]/div/button[2]")).click();
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[8]/div/button[1]")).click();
	        
	        driver.findElement(
					By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[1]/input")).clear();
	        Thread.sleep(1000);
	        driver.findElement(
					By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[1]/input")).sendKeys("192.168.1.2");
	        
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[2]/input")).clear();
	        Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[2]/input")).sendKeys("222");
			
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[3]/input")).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[9]/div[3]/input")).sendKeys("222");
			Thread.sleep(1000);
			
			driver.findElement(
			By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[1]/input")).clear();
			Thread.sleep(1000);
	        driver.findElement(
			By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[1]/input")).sendKeys("192.168.1.2");
	        
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[2]/input")).clear();
	        Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[2]/input")).sendKeys("222");
            
            driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[3]/input")).clear();
            Thread.sleep(1000);
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[10]/div[3]/input")).sendKeys("222");
			Thread.sleep(1000);
			
			driver.findElement(
			By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[1]/input")).clear();
			Thread.sleep(1000);
			driver.findElement(
			By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[1]/input")).sendKeys("192.168.1.1");
            
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[2]/input")).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[2]/input")).sendKeys("222");
	        
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[3]/input")).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[11]/div[3]/input")).sendKeys("222");
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
	        
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/input")).sendKeys("DollyTest1");
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[3]/div/input")).sendKeys("192.168.1.19");
		    driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[4]/div/input")).sendKeys("BB:BB:AA:AA:AA:AA");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[5]/div[1]/input")).sendKeys("251");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[6]/div[1]/input")).sendKeys("251");
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[6]/div[3]/button")).click();
			
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[3]/button[1]")).click();
	        Thread.sleep(3500);
			
			String webString = driver
					.findElement(By
							.xpath("//*[@id=\"app\"]/div/div[3]/div[2]/div/div[1]/div"))
					.getText();
			Thread.sleep(2000);
			Result = "更新裝置成功".equals(webString) ? true : false;
			Assert.assertTrue(Result);
			
		} catch (Exception e) {
			Boolean Result = false;
			Assert.assertTrue(Result);
			e.printStackTrace();
		}

	}


}
