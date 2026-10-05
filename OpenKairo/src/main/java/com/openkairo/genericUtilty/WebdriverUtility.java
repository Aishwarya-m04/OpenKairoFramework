package com.openkairo.genericUtilty;

import java.io.File;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility {
	
	WebDriver driver;
	// to launch browser
	public WebDriver webdriverLaunch(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("safari"))
			driver = new SafariDriver();
		else
			driver = new ChromeDriver();

		return driver;
	}
	
	// maxmise browser /////
		public void maxBrowser(WebDriver driver) {
			driver.manage().window().maximize();
		}

		// minimise browser ////
		public void minBrowser(WebDriver driver) {
			driver.manage().window().minimize();
		}

		// Syncronization ////////
		// 1.implicitWait

		public void implicitWait(WebDriver driver, int sec) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
		}

		// explicitawait
		public void explicitWait(WebDriver driver, WebElement ele, int sec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
			wait.until(ExpectedConditions.visibilityOf(ele));
		}

		// fluentWait
		public void fluentWait(WebDriver driver, WebElement ele) {
			FluentWait wait = new FluentWait(driver);
			wait.pollingEvery(Duration.ofSeconds(1));
			wait.withTimeout(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(ele));
		}

		// PopUp ///////
		// 1..Switch to alert and accept
		public void alertAndAccept(WebDriver driver) {
			driver.switchTo().alert().accept();
		}

		// 2..switch to alert and dismiss
		public void alertAndDismiss(WebDriver driver) {
			driver.switchTo().alert().dismiss();
		}

		// 3..getText from Alert
		public String alertAndgetText(WebDriver driver) {
			String text = driver.switchTo().alert().getText();
			return text;
		}

		// 4..Switch to alert and sendkeys
		public void switchAndSendkeys(WebDriver driver, String text) {
			driver.switchTo().alert().sendKeys("text");
		}

		// 5.file upload
		public void uploadFile(WebElement ele, String filePath) {
			ele.sendKeys(filePath);
		}

		// JAVASCRIPTEXECUTOR ///////

		// 1..click
		public void jseClick(WebDriver driver, WebElement ele) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
		}

		// 2.to pass Value
		public void jsePassValue(WebDriver driver, WebElement ele, String value) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='" + value + "';", ele);
		}

		// 3.Scroll into view
		public void jseScrollIntoView(WebDriver driver, WebElement ele) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", ele);
		}

		// TAKESSCREENSHOT//
		// WebElement Screenshot
		public void webelementScreenshot(WebElement ele, String eleName) throws Throwable {
			File src = ele.getScreenshotAs(OutputType.FILE);
			File dest = new File("./Screenshots/" + eleName + ".png");
			FileHandler.copy(src, dest);

		}

		// Webpage Screenshot
		public void webPageScreenshot(WebDriver driver, String pagename) throws Throwable {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("./Screenshot/" + pagename + ".png");
			FileHandler.copy(src, dest);

		}

		// SElECT CLASS ////
		// by Index
		public void selectByDD(WebElement ele, int index) {
			Select s = new Select(ele);
			s.selectByIndex(index);
		}

		// by Value
		public void selectByDD(WebElement ele, String value) {
			Select s = new Select(ele);
			s.selectByValue(value);
		}

		public void selectByDD(String visibletext, WebElement ele) {
			Select s = new Select(ele);
			s.selectByContainsVisibleText(visibletext);
		}

		// ActionClass ////
		// 1..move to element
		public void moveToElement(WebDriver driver, WebElement ele) {
			Actions a = new Actions(driver);
			a.moveToElement(ele).build().perform();
		}

		// 2..scroll to element
		public void scrollToElement(WebDriver driver, WebElement ele) {
			Actions a = new Actions(driver);
			a.scrollToElement(ele).build().perform();

		}

		// 3...drag and drop
		public void dragAndDrop(WebDriver driver, WebElement src, WebElement target) {
			Actions a = new Actions(driver);
			a.dragAndDrop(src, target).build().perform();
		}

		// 4...click and hold
		public void clickAndHold(WebDriver driver, WebElement ele, WebElement target) {
			Actions a = new Actions(driver);
			a.clickAndHold(ele).pause(Duration.ofSeconds(2)).moveToElement(target).pause(Duration.ofSeconds(2)).release()
					.build().perform();
		}

		// Handling windows ///
		// by title
		public void handlingWindowByTitle(WebDriver driver, String title) {
			Set<String> windowId = driver.getWindowHandles();
			for (String windows : windowId) {
				driver.switchTo().window(windows);
				if (driver.getTitle().contains(title))
					break;

			}
		}

		// by currntURL
		public void handlingWindowByUrl(WebDriver driver, String Url) {
			Set<String> windowID = driver.getWindowHandles();
			for (String windows : windowID) {
				driver.switchTo().window(windows);
				if (driver.getCurrentUrl().contains(Url))
					break;
			}
		}
		/// handling Frames ////
		// 1. by index

		public void switchToFrame(WebDriver driver, int index) {
			driver.switchTo().frame(index);
		}

		// 3.2.2 Handle Frames by id or Name
		public void switchToFramesByIdorName(WebDriver driver, String idOrName) {
			driver.switchTo().frame(idOrName);
		}

		// 3.2.3 Handle Frames by element
		public void switchToFramesByElement(WebDriver driver, WebElement ele) {
			driver.switchTo().frame(ele);
		}


}
