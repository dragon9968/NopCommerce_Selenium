package nopCommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class User_Login {
  
	public WebDriver driver;
    //private User_Register userRegisterPage;
	  String projectPath = System.getProperty("user.dir");

  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
  }
  @Test
	public void Login_06_Valid_Login() {
	  driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Log In']")).click();
	  driver.findElement(By.cssSelector("input#email")).sendKeys(User_Register.emailAddress);
	  driver.findElement(By.cssSelector("input#pass")).sendKeys(User_Register.password);
	  driver.findElement(By.xpath("//button[@title='Login']")).click();

	}

  @AfterClass
  public void afterClass() {
  }

}
