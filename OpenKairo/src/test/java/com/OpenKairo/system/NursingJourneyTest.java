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
import com.openkairo.objectRepository.Physician_page;

@Listeners(com.openkairo.listnerUtility.LisnerImplementationClass.class)
public class NursingJourneyTest extends BaseClass 
{
	ExcelUtility e = new ExcelUtility();
	JavaUtility j = new JavaUtility();
	WebdriverUtility w=new WebdriverUtility();
	@Test(groups = "system")
	public void nursingCareTest() throws Exception
	{
		 String nurseEmail =
		            e.readDataFromExcelFile("Login", 2, 0);

		    String nursePassword =
		            e.readDataFromExcelFile("Login", 2, 1);

		    Login_Page nurseLogin = new Login_Page(driver);

		    nurseLogin.loginAction(nurseEmail, nursePassword);

		    Reporter.log("Nurse login successful", true);

		    Thread.sleep(3000);

		    String pFirstName = e.readDataFromExcelFile("Pname", 1, 0);
		    String pLastName = e.readDataFromExcelFile("Pname", 1, 1);
		    Home_Page h1 = new Home_Page(driver);
		    h1.handlePopup();

		    Nurse_Page np = new Nurse_Page(driver);

		    // Open encounter and record vitals
		    np.addVitals(pLastName);
		    
		    Assert.assertTrue(
		    	    np.isVitalsSaved("120/80"),
		    	    "Vitals were not saved successfully"
		    	);
		    
		    Thread.sleep(2000);
		    String nurseNote = "Drink Hot Water and Avoid Outside Food";
		    np.addNote(nurseNote);

		    Assert.assertTrue(
		        np.isNurseNoteSaved(),
		        "Nurse Note was not saved successfully"
		    );
		    
		    // Logout Nurse
		    driver.navigate().back();
		    Home_Page home = new Home_Page(driver);
		    home.signOutAction();

		    
		    //============Physician===================//
		    
		    //  Login as Physician
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

		    // 8. Open same patient encounter
		    Physician_page pp = new Physician_page(driver);
		    pp.physicianAction(pLastName);

		    // 9. Review recorded information
		    Assert.assertTrue(
		        pp.isVitalsAvailable(),
		        "Vitals are not available to Physician"
		    );
		    
		    Assert.assertTrue(
		    	    pp.isNurseNoteAvailable(),
		    	    "Nurse Note is not available to Physician"
		    	);
		    
		    driver.navigate().back();
            Home_Page hp=new Home_Page(driver);
		    hp.signOutAction();

		    Reporter.log(
		            "Physician logout successful",
		            true
		    );

	}
   
}
