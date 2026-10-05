package com.OpenKairo.smoke;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.openkairo.baseTest.BaseClass;
import com.openkairo.genericUtilty.ExcelUtility;
import com.openkairo.genericUtilty.ThreadLocalUtility;
import com.openkairo.objectRepository.Home_Page;
import com.openkairo.objectRepository.Login_Page;
import com.openkairo.objectRepository.Nurse_Page;

@Listeners(com.openkairo.listnerUtility.LisnerImplementationClass.class)
public class NurseVitalsTest extends BaseClass
{
	@Test(groups = "smoke")
    public void createVitals() throws Exception 
	{
		ExcelUtility e=new ExcelUtility();
        
		//login as nurse
		
		String emailId = e.readDataFromExcelFile("Login",2,0);
		String password = e.readDataFromExcelFile("Login",2,1);
		
		Login_Page login=new Login_Page(driver);
		login.loginAction(emailId, password);
		Reporter.log("Login done",true);
		
		//read patient name
		String pFirstName = e.readDataFromExcelFile("Pname", 1, 0);
		String pLastName = e.readDataFromExcelFile("Pname", 1, 1);
		
		//handling popup
		Thread.sleep(3000);
		Home_Page h = new Home_Page(driver);
		h.getPopUp().click();
		
		Nurse_Page np=new Nurse_Page(driver);
		np.addVitals(pLastName);
		
		Assert.assertTrue(
		        np.isVitalsSaved("120/80"),
		        "Vitals were not saved successfully"
		);
		

		  System.out.println("=======Logout started======");
			Home_Page hp=new Home_Page(driver);
			driver.navigate().back();
			hp.signOutAction();
			 System.out.println("=======Logout completed======");
	
	}
}  

