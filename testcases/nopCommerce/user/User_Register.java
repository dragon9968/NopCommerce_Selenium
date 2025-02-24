package nopCommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_Register {
	
	
	 public WebDriver driver;
	 public static String emailAddress;
	 public static String password = "123456";
	  String projectPath = System.getProperty("user.dir");
	  JavascriptExecutor jsExecutor;
 @BeforeClass
 public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	jsExecutor = (JavascriptExecutor) driver;
	emailAddress = "long" + generateRandomNumber() + "@qa.team";
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.manage().window().maximize();
	driver.get("http://live.techpanda.org/index.php/");
}  
  //@Test
  public void TC_01_Register_Empty_Data() {
  driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
  driver.findElement(By.xpath("//a[@title='Register']")).click();
  driver.findElement(By.xpath("//button[@title='Register']")).click();

  Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-firstname")).getText(), "This is a required field.");
  Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-lastname")).getText(), "This is a required field.");
  Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email_address")).getText(), "This is a required field.");
  Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-password")).getText(), "This is a required field.");
  Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-confirmation")).getText(), "This is a required field.");
  }
  
  //@Test
  public void TC_02_Register_Invalid_Email() {
	  driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Register']")).click();
	  driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("long");

	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;",driver.findElement(By.xpath("//input[@id='email_address']"))), "Please enter an email address.");
     // Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;",driver.findElement(By.xpath("//input[@id='email_address']"))), "Vui lòng bao gồm '@' trong địa chỉ email. 'long' bị thiếu '@'.");

  }
  
  @Test
  public void TC_03_Register_Success() {
	  driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Register']")).click();
	  
	  driver.findElement(By.cssSelector("input#firstname")).sendKeys("long");
	  driver.findElement(By.cssSelector("input#middlename")).sendKeys("dinh");
	  driver.findElement(By.cssSelector("input#lastname")).sendKeys("nguyen");
	  driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#password")).sendKeys(password);
	  driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
	  driver.findElement(By.cssSelector("input#is_subscribed")).click();
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg")).getText(), "Thank you for registering with Main Website Store..");
	  driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
  }
  
  //@Test
  public void TC_04_Register_Existing_Email() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
  }
  
  
  @AfterClass
  public void afterClass() {
  }
  public int generateRandomNumber() {
	 Random rand = new Random();
	 return rand.nextInt(99999);
}
}
