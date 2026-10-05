package com.openkairo.listnerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.openkairo.genericUtilty.ThreadLocalUtility;


public class LisnerImplementationClass implements ITestListener,ISuiteListener {
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
  @Override
  public void onStart(ISuite suite) 
  {
	  String date=new Date().toString().replace(" ","_").replace(":", "_");
	  spark=new ExtentSparkReporter("./AdvanceReport/report_"+date+".html");
	  spark.config().setDocumentTitle("OpenKairo test suite report");
	  spark.config().setReportName("OpenKairo reports");
	  spark.config().setTheme(Theme.DARK);
	  
	  //add environment info and create test
	  
	  report=new ExtentReports();
	   report.attachReporter(spark);
		report.setSystemInfo("Browser", "Chrome-100");
		report.setSystemInfo("OS", "window-10");
  }
  @Override
  public void onFinish(ISuite suite)
  {
	  System.out.println("Report configuration");
		report.flush();
  }	
  
  @Override
  public void onTestStart(ITestResult result)
  {
	  String name = result.getMethod().getMethodName();
	  test=report.createTest(name);
	  ThreadLocalUtility.setTest(test);
	  test.log(Status.INFO,name+"================STARTED");
  }
  @Override
  public void onTestSuccess(ITestResult result)
  {
	  String name = result.getMethod().getMethodName();
	  test.log(Status.INFO,name+"=======COMPLETED=======" );
	
  }
  @Override
  public void onTestFailure(ITestResult result)
  {
	//to take sc with ExtentReport
	  String TestName=result.getMethod().getMethodName();
	  TakesScreenshot ts=(TakesScreenshot)ThreadLocalUtility.getDriver();
		String filePath=ts.getScreenshotAs(OutputType.BASE64);
		String date = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath, TestName+"_"+date);
		test.log(Status.FAIL, TestName+"=======FAILED=======");
	  
  }
	
}
