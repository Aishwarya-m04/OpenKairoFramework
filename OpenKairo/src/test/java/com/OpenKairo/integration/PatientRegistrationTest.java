package com.OpenKairo.integration;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Report;
import com.openkairo.baseTest.BaseClass;
import com.openkairo.genericUtilty.ExcelUtility;
import com.openkairo.genericUtilty.JavaUtility;
import com.openkairo.objectRepository.Home_Page;
import com.openkairo.objectRepository.Login_Page;
import com.openkairo.objectRepository.Patient_Page;

@Listeners(com.openkairo.listnerUtility.LisnerImplementationClass.class)
public class PatientRegistrationTest extends BaseClass
{
	
	@Test(groups = "integration")
      public void patientRegistertest() throws Exception
      {
    	  ExcelUtility e = new ExcelUtility();
  		JavaUtility j = new JavaUtility();
  		
  		//login as receptionist
  		String emailId = e.readDataFromExcelFile("Login",1,0);
  		String password = e.readDataFromExcelFile("Login",1,1);
  		

  		Login_Page login=new Login_Page(driver);
  		login.loginAction(emailId, password);
  		Reporter.log("Receptionist Login done",true);

  		//enter patient name
  		String pFirstName = e.readDataFromExcelFile("Pname", 1, 0);
  		String pLastName = e.readDataFromExcelFile("Pname", 1, 1);
  		
  		Thread.sleep(3000);
  		Home_Page h = new Home_Page(driver);
  		h.getPopUp().click();
  		
  		
  		String date = j.currentDate();
  		String time = j.currentTime();
  		String am_pm = j.currentTimeAMPm();
  		Patient_Page pp = new Patient_Page(driver);
  		pp.createPatient(pFirstName, pLastName);
  		Reporter.log("====Patient created============");
  		
  		 Reporter.log("=======Receptionist Logout started======",true);
			Home_Page hp=new Home_Page(driver);
			hp.signOutAction();
	    Reporter.log("=======receptionist Logout completed======",true);
	
      }
}
