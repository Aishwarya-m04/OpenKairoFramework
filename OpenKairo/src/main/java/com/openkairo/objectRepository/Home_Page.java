package com.openkairo.objectRepository;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openkairo.genericUtilty.WebdriverUtility;

public class Home_Page {
	WebDriver driver;
	public  Home_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[contains(.,'Got it')]")
	private WebElement PopUp;
	
	
	public WebElement getPopUp() {
		return PopUp;
	}

	@FindBy(xpath = "//span[text()='Patient']")
	private WebElement PatientButton;
	
	@FindBy(xpath = "//span[text()='Book']")
	private WebElement BookButton;
	
	@FindBy(xpath = "//span[text()='Sign out']")
	private WebElement SignOutButton;
	
	@FindBy(xpath = "//span[text()='Today']")
	private WebElement TodayButton;
	
	@FindBy(xpath = "//span[text()='Wards']")
	private WebElement WardsButton;
	
	@FindBy(xpath = "//a[contains(.,'Laboratory')]")
	private WebElement LaboratoryButton;
	
	@FindBy(xpath = "//h2[text()='Medical Ward']")
	private WebElement MedicaLWard;
	
	@FindBy(xpath = "//h2[text()='Surgical Ward']")
	private WebElement SurgicalWard;
	
	//create  new patient and book visit
	
	@FindBy(xpath = "//button[text()='+ New patient']")
	private WebElement NewPatient;
	
	@FindBy(xpath = "//input[@placeholder='First name *']")
	private WebElement PatientFirstName;
	
	@FindBy(xpath = "//button[text()='Create patient']")
	private WebElement CreatePatientButton;
	
	@FindBy(xpath = "//button[text()='Book visit']")
	private WebElement BookVisitButton;
	
	@FindBy(xpath = "//input[@placeholder='Last name *']")
	private WebElement PatientLastName;
	
	@FindBy(xpath = "//input[@type='datetime-local']")
	private WebElement DateTime;
	
	//gettters

	public WebElement getPatientButton() {
		return PatientButton;
	}

	public WebElement getBookButton() {
		return BookButton;
	}

	public WebElement getSignOutButton() {
		return SignOutButton;
	}

	public WebElement getTodayButton() {
		return TodayButton;
	}

	public WebElement getWardsButton() {
		return WardsButton;
	}

	public WebElement getLaboratoryButton() {
		return LaboratoryButton;
	}

	public WebElement getMedicaLWard() {
		return MedicaLWard;
	}

	public WebElement getSurgicalWard() {
		return SurgicalWard;
	}
	
	public void signOutAction()
	{
		WebdriverUtility w=new WebdriverUtility();
		w.explicitWait(driver, SignOutButton, 15);
		Actions a=new Actions(driver);
		a.moveToElement(SignOutButton).pause(Duration.ofSeconds(2)).click().build().perform();
	}

	
	
	
	

}
