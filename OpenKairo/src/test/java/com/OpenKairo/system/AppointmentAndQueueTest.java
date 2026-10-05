package com.OpenKairo.system;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.openkairo.baseTest.BaseClass;
import com.openkairo.genericUtilty.ExcelUtility;
import com.openkairo.genericUtilty.JavaUtility;
import com.openkairo.genericUtilty.WebdriverUtility;
import com.openkairo.objectRepository.Home_Page;
import com.openkairo.objectRepository.Login_Page;
import com.openkairo.objectRepository.Patient_Page;
import com.openkairo.objectRepository.Physician_page;
@Listeners(com.openkairo.listnerUtility.LisnerImplementationClass.class)
public class AppointmentAndQueueTest extends BaseClass 
{
	ExcelUtility e = new ExcelUtility();
	JavaUtility j = new JavaUtility();
	WebdriverUtility w=new WebdriverUtility();

	@Test(groups = "system")
	public void appointmentJourneyTest() throws Exception
	{
		//====================================================
		//          reception
		//=====================================================
	    // Login as Receptionist
	    String emailId = e.readDataFromExcelFile("Login", 1, 0);
	    String password = e.readDataFromExcelFile("Login", 1, 1);

	    Login_Page login = new Login_Page(driver);
	    login.loginAction(emailId, password);

	    Reporter.log("Receptionist login successful", true);

	    // Patient data
	    String pFirstName = e.readDataFromExcelFile("Pname", 1, 0);
	    String pLastName = e.readDataFromExcelFile("Pname", 1, 1);

	    Thread.sleep(3000);

	    Home_Page h = new Home_Page(driver);
	    h.getPopUp().click();

	    String date = j.currentDate();
		String time = j.currentTime();
		String am_pm = j.currentTimeAMPm();
	    Patient_Page pp = new Patient_Page(driver);

	    // Book appointment
	    pp.bookVisit(pFirstName, date, time, am_pm);

	    Reporter.log("Appointment booked successfully", true);
	    
	    //verify front desk
	    Assert.assertTrue(
	            pp.isPatientInFrontDeskQueue(pLastName),
	            "Patient was not found in Front Desk Queue"
	    );
	    
	    //signout as receptionist
	    h.signOutAction();

	    Reporter.log("Receptionist logout successful", true);
	    

	    // =========================
	    // PHYSICIAN
	    // =========================

	    String physicianEmail =
	            e.readDataFromExcelFile("Login", 3, 0);

	    String physicianPassword =
	            e.readDataFromExcelFile("Login", 3, 1);


	    Login_Page physicianLogin =
	            new Login_Page(driver);

	    physicianLogin.loginAction(
	            physicianEmail,
	            physicianPassword
	    );

	    Reporter.log("Physician login successful", true);


	    Thread.sleep(3000);

	    Home_Page hp = new Home_Page(driver);

	   // hp.getPopUp().click();


	    // Open encounter
	    Physician_page physician =
	            new Physician_page(driver);

	    physician.physicianAction(pLastName);


	    // Verify encounter
	    Assert.assertTrue(
	            physician.isVitalsAvailable(),
	            "Patient encounter was not opened"
	    );

	    Reporter.log(
	            "Patient encounter opened successfully",
	            true
	    );


	    // Logout Physician
	    driver.navigate().back();

	    hp.signOutAction();

	    Reporter.log(
	            "Physician logout successful",
	            true
	    );
	
   }
}
