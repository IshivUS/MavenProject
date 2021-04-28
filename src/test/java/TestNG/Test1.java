package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {

	@Test(priority=1, dependsOnMethods="MagentoRegister", enabled=false)
	public void MagentoLogin() {
		//below command will automatically download webdriver code 
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://magento.com");
		driver.manage().window().maximize();
		//below command works for the wait to minimize according to the browser loading capacity
		WebDriverWait wait = new WebDriverWait(driver,30);	 
		
		//driver.findElement(By.xpath("//*[@id=\"gnav_509\"]/span/span/span")).click();
		driver.findElement(By.id("gnav_509")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys("krishnajaya28@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("K12345J");
		driver.findElement(By.name("send")).click();
		
		//below is the hard coded command to wait for selenium to wait irrespecitive of browser's response time
		//Thread.sleep(5000);
		
		//below command instructs selenium to wait till the condition is matched and then come out.
		wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div"),"Invalid login or password."));
		
		String errorMsg= driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
		System.out.println(errorMsg);
			
		driver.quit();
	}
	
	@Test(priority=2)
	public void MagentoRegister() {
		//below command will automatically download webdriver code 
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://magento.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("gnav_509")).click();
		driver.findElement(By.id("register")).click();
		driver.findElement(By.name("firstname")).sendKeys("krishna");
		driver.findElement(By.name("lastname")).sendKeys("jaya");
		driver.findElement(By.name("email")).sendKeys("krishnajaya28@gmail.com");
		driver.findElement(By.id("self_defined_company")).sendKeys("TCS");
		
		//To select from UI drop down we need to import Select class
		//in below command, we are declaring the Company Type drop-down element as an instance of the "Select" class 
		Select cmy_type = new Select(driver.findElement(By.name("company_type")));  
		
		//We can now control "cmy_type" by using any of the available Select methods to 
		//select dropdown in Selenium. The code below will select the option "Tech Partner"
		cmy_type.selectByVisibleText("Tech Partner");
		
		Select role = new Select(driver.findElement(By.name("individual_role")));
		role.selectByVisibleText("Technical/developer");
		
		Select ctry = new Select(driver.findElement(By.id("country")));
		if(ctry.getFirstSelectedOption().getText().contentEquals("United States"))
		{
			ctry.getFirstSelectedOption();
		}
		else
		{
			ctry.selectByVisibleText("United States");
		}
		
		driver.findElement(By.id("password")).sendKeys("K123456789j");
		driver.findElement(By.name("password_confirmation")).sendKeys("K123456789j");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
		driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).click();
		
		//below code checks if by default the check box is checked, if not selects it.
		if(!driver.findElement(By.id("agree_terms")).isSelected())
		{
		driver.findElement(By.id("agree_terms")).click();
		}
	}
}
