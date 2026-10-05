package com.openkairo.baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.openkairo.genericUtilty.ExcelUtility;
import com.openkairo.genericUtilty.JavaUtility;
import com.openkairo.genericUtilty.PropertyUtility;
import com.openkairo.genericUtilty.ThreadLocalUtility;
import com.openkairo.genericUtilty.WebdriverUtility;
import com.openkairo.objectRepository.Home_Page;
import com.openkairo.objectRepository.Login_Page;



/**
 * This class contains pre conditions and post conditions 
 * Testng annotations are used
 * @author Aishwarya
 */


public class BaseClass {
	public WebDriver driver;
	WebdriverUtility wlib=new WebdriverUtility();
	JavaUtility jlib=new JavaUtility();
	ExcelUtility elib=new ExcelUtility();
	PropertyUtility plib=new PropertyUtility();
	
  @BeforeSuite(groups = {"smoke","integration","system"})
  public void configBS()
  {
	  //DataBase connection
  }
  
 // @Parameters("browser")                                           //add parameters while parallel execution
  @BeforeClass(groups = {"smoke","integration","system"})
  public void configBC() throws Exception            //insert String browser inside method for parallel execution
  {
	  //Launch Browser
	  System.out.println("====launch the browser=====");
	  String Browser=plib.toReadDataFromPropertyFile("browser");    //comment while parallel execution
	  driver=wlib.webdriverLaunch(Browser);
	  
	  //for parallel execution
	  ThreadLocalUtility.setDriver(driver);       ///to avoid disadvg of static var in parrallel excn used this from utility
   	wlib.maxBrowser(driver);
	  
  }
  @BeforeMethod(groups = {"smoke","integration","system"})
  public void configBM() throws Exception
  {
	  System.out.println("====== Login ======");
	  
	  String url = plib.toReadDataFromPropertyFile("url");
	  	//store in a variable and call login method from login page
		driver.get(url);
//		String emailId = elib.readDataFromExcelFile("Login",1,0);
//		String password = elib.readDataFromExcelFile("Login",1,1);
//		
//		//write after writing pom
//		Login_Page login=new Login_Page(driver);
//		login.loginAction(emailId, password);
//		Reporter.log("Login done",true);
	  
  }
  @AfterMethod(groups = {"smoke","integration","system"})
  public void configAM()
 {
//	  System.out.println("=======Logout started======");
//		Home_Page hp=new Home_Page(driver);
//		hp.signOutAction();
//		 System.out.println("=======Logout completed======");
  }
  @AfterClass(groups = {"smoke","integration","system"})
  public void configAC()
  {
	  System.out.println("=====Close the Browser=====");
		driver.quit();
	  
  }
  @AfterSuite(groups = {"smoke","integration","system"})
  public void configAS()
  {
	  //database close
  }

}
