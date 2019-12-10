package svv.selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;


import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;  


@TestInstance(Lifecycle.PER_CLASS)
class TestSuite {
	
	WebDriver driver;
	
	
	@BeforeAll
	public  void setup() {
		
	    System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");  
	    driver=new ChromeDriver();
		
	}

	
	@Test
	@DisplayName("User Already Exists")
	public void testcase1() {
	    driver.navigate().to("http://svvtodo.herokuapp.com/register");  
	    
	    
	    driver.findElement(By.id("email")).sendKeys("ronwayachal@gmail.com");
	    driver.findElement(By.id("pass")).sendKeys("one");
	    driver.findElement(By.id("cpass")).sendKeys("one");
	  	   
	    driver.findElement(By.xpath("//button")).click();

	    WebElement error=driver.findElement(By.id("error"));
	    WebDriverWait wait = new WebDriverWait(driver,10);

	    wait.until(ExpectedConditions.textToBePresentInElement(error, "User already exist"));
	    
	    String expected=error.getText();
	    assertEquals(expected, "User already exist");
	    
	    
	 
	}
	
	@Test
	@DisplayName("Password Match")
	public void testcase2() {
		
		
	    driver.navigate().to("http://svvtodo.herokuapp.com/register");  
	    
	    
	    driver.findElement(By.id("email")).sendKeys("ronwayachal@gmail.com");
	    driver.findElement(By.id("pass")).sendKeys("one");
	    driver.findElement(By.id("cpass")).sendKeys("two");
	  	   
	    driver.findElement(By.xpath("//button")).click();

	    WebElement error=driver.findElement(By.id("error"));
	    WebDriverWait wait = new WebDriverWait(driver,10);

	    
	    wait.until(ExpectedConditions.textToBePresentInElement(error, "password doesnt match"));
	    
	    String expected=error.getText();
	    assertEquals(expected, "password doesnt match");
	    
	}
	
	@Test
	@DisplayName("Field Blank")
	public void testcase3() {
		
		
	    driver.navigate().to("http://svvtodo.herokuapp.com/register");  
	    
	    
	    driver.findElement(By.id("email")).sendKeys("ronwayachal@gmail.com");

	  	   
	    driver.findElement(By.xpath("//button")).click();

	    WebElement error=driver.findElement(By.id("error"));
	    WebDriverWait wait = new WebDriverWait(driver,10);

	    
	    wait.until(ExpectedConditions.textToBePresentInElement(error, "Field Cannot Be Blank"));
	    
	    
	    String expected=error.getText();
	    assertEquals(expected, "Field Cannot Be Blank");
	}
	

	
	@Test
	@DisplayName("Delete Confirmation")
	public void testcase4() {
		
		
	    driver.navigate().to("http://svvtodo.herokuapp.com");  
	   
	    driver.findElement(By.id("user")).sendKeys("ronwayachal@gmail.com");
	    driver.findElement(By.id("pass")).sendKeys("one");

	    driver.findElement(By.xpath("//button")).click();
	    
	    
	    WebDriverWait wait = new WebDriverWait(driver,5);
	    
	    wait.until(ExpectedConditions.urlToBe("http://svvtodo.herokuapp.com/home"));
	    
	    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		      
	    driver.findElement(By.xpath("//html/body/main/div/div[1]/div/div[1]/div[1]/div/div[2]/button")).click();
	    

	    try {
	    	
	    wait.until(ExpectedConditions.alertIsPresent());
	
	    }
	    catch(Exception e) {
	    	
	    	fail("no confirmation");
	    }


	}
	
	@Test
	@DisplayName("Duplicate todo")
	public void testcase5() {
		
		
	    driver.navigate().to("http://svvtodo.herokuapp.com");  
	   
	    driver.findElement(By.id("user")).sendKeys("ronwayachal@gmail.com");
	    driver.findElement(By.id("pass")).sendKeys("one");

	    driver.findElement(By.xpath("//button")).click();
	    
	    
	    WebDriverWait wait = new WebDriverWait(driver,5);
	    
	    wait.until(ExpectedConditions.urlToBe("http://svvtodo.herokuapp.com/home"));
	    
	    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	  	   
	    List<WebElement> todos=driver.findElements(By.className("p-2"));
	    
	    
	    
	    HashMap<String,Integer> hm=new HashMap<String, Integer>();
	    
	    
	    for(WebElement elem:todos) {
	    	
	    	
	    	String title=elem.getText();
	    		    	
	    	if (hm.containsKey(title))
	    	{

	    		fail("duplicate present");

	    	}
	    	else
	    	{
	    		hm.put(title,1);
	    	} 	
	    	
	    }

	    
	}
	
	
	@AfterAll
	public void close_driver() {
		driver.close();
	}

}
