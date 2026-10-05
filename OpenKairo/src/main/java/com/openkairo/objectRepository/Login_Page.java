package com.openkairo.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openkairo.genericUtilty.WebdriverUtility;

public class Login_Page 
{

	 WebDriver driver;
		public Login_Page(WebDriver driver) 
		{
			this.driver=driver;
		   PageFactory.initElements(driver, this);

	    }
		@FindBy(id = "email")
		private WebElement Email;
		
		@FindBy(id = "password")
		private WebElement Password;
		
		@FindBy(xpath = "//button[text()='Sign in']")
		private WebElement SignInButton;
		
		
		public WebElement getEmail() {
			return Email;
		}

		public WebElement getPassword() {
			return Password;
		}

		public WebElement getSignInButton() {
			return SignInButton;
		}
		
		public void loginAction(String email,String password) 
		{
			WebdriverUtility w=new WebdriverUtility();
			w.explicitWait(driver, Email, 15);
			Email.clear();
			Email.sendKeys(email);
			w.explicitWait(driver, Password, 15);
			Password.clear();
			Password.sendKeys(password);
			SignInButton.click();
			
		}
		

}
