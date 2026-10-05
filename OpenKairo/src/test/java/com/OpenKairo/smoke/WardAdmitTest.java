package com.OpenKairo.smoke;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.openkairo.baseTest.BaseClass;
import com.openkairo.genericUtilty.ExcelUtility;
import com.openkairo.objectRepository.Home_Page;
import com.openkairo.objectRepository.Login_Page;
import com.openkairo.objectRepository.Patient_Page;

@Listeners(com.openkairo.listnerUtility.LisnerImplementationClass.class)
public class WardAdmitTest extends BaseClass{
	
	@Test(groups = "smoke")
	public void wardAdmitTest() throws Exception
	{
		ExcelUtility e = new ExcelUtility();
		
		//login as receptionist
		String emailId = e.readDataFromExcelFile("Login",1,0);
		String password = e.readDataFromExcelFile("Login",1,1);
		
		Login_Page login=new Login_Page(driver);
		login.loginAction(emailId, password);
		Reporter.log("Login done",true);
		
		//enter patient name
	    String pFirstName = e.readDataFromExcelFile("Pname", 1, 0);
				String pLastName = e.readDataFromExcelFile("Pname", 1, 1);
				
				//handling popup
				Thread.sleep(3000);
				Home_Page h = new Home_Page(driver);
				h.handlePopup();
				
				//ward creation
				Patient_Page pp=new Patient_Page(driver);
				pp.addPatientToWard(pLastName);
				
				System.out.println("=======Logout started======");
				Home_Page hp=new Home_Page(driver);
				driver.navigate().back();
				hp.signOutAction();
				 System.out.println("=======Logout completed======");
		
		
	}

}
