package com.openkairo.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LabTech_Page 
{
	WebDriver driver;
	public LabTech_Page(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath = "//input[@placeholder='Find in queue…']")
	private WebElement SearchPatient;
	
	@FindBy(xpath = "//span[text()='Open']")
	private WebElement PatientOpen;
	
	

}
