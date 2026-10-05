package com.openkairo.objectRepository;



import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openkairo.genericUtilty.WebdriverUtility;

public class Patient_Page {
	WebdriverUtility w=new WebdriverUtility();
	WebDriver driver;
	public Patient_Page(WebDriver driver) 
	{
		
		PageFactory.initElements(driver, this);
			
	}
	
	//create  new patient and book visit
	
	@FindBy(xpath = "//span[text()='Patient']")
	private WebElement PatientButton;
	
	@FindBy(xpath = "//input[@placeholder='Search by name, MRN, or phone…']")
	private WebElement SearchPatient;
	
	////input[@placeholder='Search name, MRN or phone…']
	
	@FindBy(xpath = "//button[text()='+ New patient']")
	private WebElement NewPatient;
	
	@FindBy(xpath = "//input[@placeholder='First name *']")
	private WebElement PatientFirstName;
	
	@FindBy(xpath = "//button[text()='Add to queue']")
	private WebElement CreatePatientButton;
	
	@FindBy(xpath = "//div[@class='flex justify-end gap-2 pt-1']/descendant::button[text()='Book visit']")
	private WebElement BookVisitButton;
	
	@FindBy(xpath = "//input[@placeholder='Last name *']")
	private WebElement PatientLastName;
	
	@FindBy(xpath = "//input[@type='datetime-local']")
	private WebElement DateTime;
	
	@FindBy(xpath = "//span[text()='Book']")
	private WebElement BookButton;
	
	@FindBy(xpath = "//span[@class='text-sm truncate']")
	private WebElement autoSelect;
	
	//ward
	
	@FindBy(xpath = "//span[text()='Wards']")
	private WebElement WardsButton;
	
	@FindBy(xpath = "//button[text()='Admit']")
	private WebElement AdmitButton;
	
	@FindBy(xpath = "//input[@placeholder='Search name, MRN or phone…']")
	private WebElement searchPatientButton;
	
	@FindBy(xpath = "//button[@type='button']")
	private WebElement WardDD;
	
	@FindBy(xpath = "//span[text()='Medical Ward']")
	private WebElement MedicalWardDD;
	
	@FindBy(xpath = "//span[text()='Surgical Ward']")
	private WebElement SurgicalWardDD;
	
	@FindBy(xpath = "//p[@class='text-xs text-muted-foreground font-mono']")
	private WebElement SelectPatient;
	

	@FindBy(xpath = "//h2[text()='Medical Ward']")
	private WebElement MedicaLWard;
	
	@FindBy(xpath = "//h2[text()='Surgical Ward']")
	private WebElement SurgicalWard;
	
	@FindBy(xpath = "//div[@class='flex justify-end gap-2']/child::button[text()='Admit']")
	private WebElement ConfirmAdmitButton;
	
	//patientcrated
	@FindBy(xpath = "//button/p")
	private List<WebElement> CreatedPatientVerify;
	
	//GETTERS
	
    
	public WebElement getWardDD() {
		return WardDD;
	}
	public WebElement getAdmitButton() {
		return AdmitButton;
	}

	public WebElement getMedicalWardDD() {
		return MedicalWardDD;
	}
	
	public WebElement getSurgicalWardDD() {
		return SurgicalWardDD;
	}

	public WebElement getWardsButton() {
		return WardsButton;
	}

	public WebElement getMedicaLWard() {
		return MedicaLWard;
	}

	public WebElement getSurgicalWard() {
		return SurgicalWard;
	}

	public WebElement getPatientButton() {
		return PatientButton;
	}
	
	public WebElement getNewPatient() {
		return NewPatient;
	}

	public WebElement getPatientFirstName() {
		return PatientFirstName;
	}

	public WebElement getCreatePatientButton() {
		return CreatePatientButton;
	}

	public WebElement getBookVisitButton() {
		return BookVisitButton;
	}

	public WebElement getPatientLastName() {
		return PatientLastName;
	}

	public WebElement getDateTime() {
		return DateTime;
	}
	
	public WebElement getBookButton() {
		return BookButton;
	}
	
	public WebElement getSearchPatient() {
		return SearchPatient;
	}
	
	public void createPatient(String FirstName,String LastName) throws InterruptedException 
	{
		w.explicitWait(driver, PatientButton, 15);
		PatientButton.click();
	    w.explicitWait(driver, NewPatient, 15);
		NewPatient.click();
		w.explicitWait(driver, PatientFirstName, 15);
	    PatientFirstName.sendKeys(FirstName);
	    w.explicitWait(driver, PatientLastName, 15);
	    PatientLastName.sendKeys(LastName);
	    w.explicitWait(driver, CreatePatientButton, 15);
	    CreatePatientButton.click();
	
	}
	
	public void bookVisit(String PatientName,String CurrentDate, String currentTime, String ampm) throws InterruptedException
	{
		w.explicitWait(driver, BookButton, 15);
		BookButton.click();
		w.explicitWait(driver, SearchPatient, 15);
		SearchPatient.sendKeys(PatientName);
		w.explicitWait(driver, autoSelect, 15);
		autoSelect.click();
		w.explicitWait(driver, DateTime, 15);
		DateTime.clear();
		DateTime.sendKeys(CurrentDate, Keys.ARROW_RIGHT, currentTime, Keys.ARROW_RIGHT,  ampm,Keys.ENTER);
		w.explicitWait(driver, BookVisitButton, 15);
		BookVisitButton.click();	
		
	}
	
	public void addPatientToWard(String PatientName) 
	{
		
		w.explicitWait(driver, WardsButton, 15);
		WardsButton.click();
		w.explicitWait(driver, AdmitButton, 15);
		AdmitButton.click();
		w.explicitWait(driver, searchPatientButton, 15);
		searchPatientButton.sendKeys(PatientName);
		w.explicitWait(driver, SelectPatient, 15);
		SelectPatient.click();
	//	WardDD.click();
		w.explicitWait(driver, AdmitButton, 15);
        ConfirmAdmitButton.click();
	}
	
	public boolean isPatientCreated(String lastName)
	{
	    WebdriverUtility w = new WebdriverUtility();

	    for (WebElement patient : CreatedPatientVerify)
	    {
	        if (patient.getText().contains(lastName))
	        {
	            return true;
	        }
	    }

	    return false;
	}
	
	public boolean isPatientInFrontDeskQueue(String lastName)
	{
	    for (WebElement patient : CreatedPatientVerify)
	    {
	        if (patient.getText().contains(lastName))
	        {
	            return true;
	        }
	    }

	    return false;
	}

}
