package com.wistron.selenium.SeleniumService.testcase;

import org.junit.Test;
import org.openqa.selenium.By;

import com.wistron.selenium.SeleniumService.Constant;
import com.wistron.selenium.SeleniumService.SeleniumTest;

import junit.framework.Assert;

public class Test003 extends BaseTest {

	@Test
	public void test003() throws Exception {
		try {
			Thread.sleep(3000);
			driver.get(Constant.PROJECT_SETUP);
			Thread.sleep(1000);
			Boolean Result = null;
			driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[2]/div/table/tbody/tr[1]/td[4]/button[2]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[3]/button[1]")).click();
			Thread.sleep(1000);
			String webString = driver
					.findElement(By
							.xpath("//*[@id=\"app\"]/div/div[3]/div[2]/div/div[1]/div"))
					.getText();
			Thread.sleep(2000);
			Result = "刪除裝置成功".equals(webString) ? true : false;
			Assert.assertTrue(Result);
			
		} catch (Exception e) {
			Boolean Result = false;
			Assert.assertTrue(Result);
			e.printStackTrace();
		}

	}


}
