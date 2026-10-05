package com.openkairo.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openkairo.genericUtilty.WebdriverUtility;

public class Nurse_Page {
	WebDriver driver;
	public Nurse_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Open']")
	private WebElement Open;
	
	@FindBy(xpath = "//input[@placeholder='Find in queue…']")
	private WebElement findInQueue;
	
	@FindBy(xpath = "//button[text()='Add Block']")
	private WebElement AddBlock;
	
	@FindBy(xpath = "//p[text()='Vitals']")
	private WebElement Vitals;
	
	@FindBy(xpath = "//input[@inputmode='numeric']")
	private WebElement BPText;
	
	@FindBy(xpath = "//button[text()='Record Vitals']")
	private WebElement RecordVitalsButton;
	
	@FindBy(xpath = "//span[normalize-space()='BP']/following-sibling::span[1]")
	private WebElement savedBP;
	
	@FindBy(xpath = "//p[text()='Nurse Note']")
	private WebElement NurseNote;
	
	@FindBy(xpath = "//textarea[contains(@placeholder,'Add a nursing log entry')]")
	private WebElement NotetextArea;
	
	@FindBy(xpath = "//button[text()='Save log']")
	private WebElement SaveNote;
	
	@FindBy(xpath = "//span[text()='Nurse Note']")
	private WebElement VerifyNurseNote;
	
	//gettes
	public WebElement getAddBlock() {
		return AddBlock;
	}

	public WebElement getVitals() {
		return Vitals;
	}

	public WebElement getBPText() {
		return BPText;
	}

	public WebElement getRecordVitalsButton() {
		return RecordVitalsButton;
	}
	
	public void addVitals(String PFname) throws InterruptedException
	{
		WebdriverUtility w=new WebdriverUtility();
		w.explicitWait(driver, findInQueue, 15);
		findInQueue.sendKeys(PFname);
		Open.click();
		w.explicitWait(driver, AddBlock, 15);
		AddBlock.click();
		w.explicitWait(driver, Vitals, 15);
		Vitals.click();
		w.explicitWait(driver, BPText, 15);
		BPText.clear();
		BPText.sendKeys("120/80");
		Thread.sleep(1500);
		RecordVitalsButton.click();
		
		
	}
	
	public void addNote(String note)
	{
		WebdriverUtility w=new WebdriverUtility();
		w.explicitWait(driver, AddBlock, 15);
		AddBlock.click();
		w.explicitWait(driver, NurseNote, 15);
		NurseNote.click();
		w.explicitWait(driver, NotetextArea, 15);
		NotetextArea.sendKeys(note);
		w.explicitWait(driver, SaveNote, 15);
		SaveNote.click();
		
	}
	
	public boolean isVitalsSaved(String expectedBP )
	
		{
		    WebdriverUtility w = new WebdriverUtility();

		    w.explicitWait(driver, savedBP, 15);

		    String actualBP = savedBP.getText();

			return actualBP.equals(expectedBP);
		}
	
	public boolean isNurseNoteSaved()
	{
	    WebdriverUtility w = new WebdriverUtility();

	    w.explicitWait(driver, VerifyNurseNote, 15);

	    return VerifyNurseNote.isDisplayed();
	}
		
}    


