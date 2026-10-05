package com.openkairo.genericUtilty;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ThreadLocalUtility {
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	
	
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}
	public static void setDriver(WebDriver actdriver) {
		driver.set(actdriver);
	}
	
	
	public static ExtentTest getTest() {
		return test.get();
	}
	public static WebDriver getDriver() {
		return driver.get();
	}

}
