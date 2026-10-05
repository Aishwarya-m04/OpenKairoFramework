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
import com.openkairo.objectRepository.Nurse_Page;
import com.openkairo.objectRepository.Patient_Page;
import com.openkairo.objectRepository.Physician_page;
@Listeners(com.openkairo.listnerUtility.LisnerImplementationClass.class)
public class PatientJourneyTest1 extends BaseClass{
	ExcelUtility e = new ExcelUtility();
	JavaUtility j = new JavaUtility();
	WebdriverUtility w=new WebdriverUtility();
	
	@Test(groups = "system")
	public void patientJourneyTest() throws Exception
	{
	    // =====================================================
	    // RECEPTIONIST
	    // =====================================================

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
	    h.handlePopup();

	    String date = j.currentDate();
	    String time = j.currentTime();
	    String am_pm = j.currentTimeAMPm();

	    Patient_Page pp = new Patient_Page(driver);

	    // Register patient
	    pp.createPatient(pFirstName, pLastName);

	    Reporter.log("Patient registered successfully", true);

	    // Book visit
	    pp.bookVisit(pFirstName, date, time, am_pm);

	    Reporter.log("Patient visit booked successfully", true);

	    // Verify patient appears in Front Desk queue
	    Assert.assertTrue(
	            pp.isPatientCreated(pLastName),
	            "Patient was not found in Front Desk queue"
	    );

	    Reporter.log(
	            "Patient found in Front Desk queue: " + pLastName,
	            true
	    );

	    // Logout Receptionist
	    h.signOutAction();

	    Reporter.log("Receptionist logout successful", true);


	    // =====================================================
	    // NURSE
	    // =====================================================

	    String nurseEmail =
	            e.readDataFromExcelFile("Login", 2, 0);

	    String nursePassword =
	            e.readDataFromExcelFile("Login", 2, 1);

	    Login_Page nurseLogin = new Login_Page(driver);

	    nurseLogin.loginAction(nurseEmail, nursePassword);

	    Reporter.log("Nurse login successful", true);

	    Thread.sleep(3000);

	    Home_Page h1 = new Home_Page(driver);
	    //h1.getPopUp().click();

	    Nurse_Page np = new Nurse_Page(driver);

	    // Open encounter and record vitals
	    np.addVitals(pLastName);

	    // Verify vitals saved
	    Assert.assertTrue(
	            np.isVitalsSaved("120/80"),
	            "Vitals were not saved successfully"
	    );

	    Reporter.log("Vitals saved successfully", true);

	    // Logout Nurse
	    driver.navigate().back();
	    h1.signOutAction();

	    Reporter.log("Nurse logout successful", true);
	    
	 // =====================================================
	    // PHYSICIAN
	    // =====================================================

	    String physicianEmail =
	            e.readDataFromExcelFile("Login", 3, 0);

	    String physicianPassword =
	            e.readDataFromExcelFile("Login", 3, 1);

	    String pLastName1 = e.readDataFromExcelFile("Pname", 1, 1);
	    Login_Page physicianLogin = new Login_Page(driver);

	    physicianLogin.loginAction(
	            physicianEmail,
	            physicianPassword
	    );
	    
	    Reporter.log("Physician login successful", true);

	    Thread.sleep(3000);

	    Home_Page h2 = new Home_Page(driver);
	   // h2.getPopUp().click();

	    Physician_page physician =
	            new Physician_page(driver);
	    
	    physician.physicianAction(pLastName1);
	    
	    Assert.assertTrue(
	            physician.isVitalsAvailable(),
	            "Vitals are not available to Physician"
	    );
	}

}
