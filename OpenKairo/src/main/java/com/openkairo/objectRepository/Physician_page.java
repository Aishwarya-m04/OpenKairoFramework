package com.openkairo.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openkairo.genericUtilty.WebdriverUtility;

public class Physician_page {
	WebDriver driver;
	
	public Physician_page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Find in queue…']")
	private WebElement physiciansearchPatient;
	
	@FindBy(xpath = "//button[@title='Open the encounter']")
	private WebElement OpenEncounter;
	
	@FindBy(xpath = "//span[@class='text-xs font-semibold tabular-nums shrink-0' and text()='120/80']")
	private WebElement Availablevitals;
	
	@FindBy(xpath = "//span[text()='Nurse Note']")
	private WebElement VerifyNurseNote;
	
	public void physicianAction(String pLastName)
	{
		WebdriverUtility w=new WebdriverUtility();
		w.explicitWait(driver, physiciansearchPatient, 15);
		physiciansearchPatient.sendKeys(pLastName);
		w.explicitWait(driver, OpenEncounter, 15);
		OpenEncounter.click();
			
	}
	
	public boolean isVitalsAvailable()
	{
	    WebdriverUtility w = new WebdriverUtility();

	    w.explicitWait(driver, Availablevitals, 15);

	    return Availablevitals.isDisplayed();
	}
	
	public boolean isNurseNoteAvailable()
	{
	    WebdriverUtility w = new WebdriverUtility();

	    w.explicitWait(driver, VerifyNurseNote, 15);

	    return VerifyNurseNote.isDisplayed();
	}

}
