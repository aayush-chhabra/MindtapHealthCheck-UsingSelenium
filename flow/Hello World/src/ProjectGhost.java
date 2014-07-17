import java.io.*;
import java.util.*;

import javax.script.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProjectGhost{
    public static void main(String[] args) throws Exception {
    	
    	try
    	{
    	// The Firefox driver supports javascript  
        WebDriver driver = new FirefoxDriver();
    		
        driver.get("https://login.cengage.com/");
        
        System.out.print("LOGIN PAGE LOADING : ");
        System.out.println(((JavascriptExecutor)driver).executeScript("var performance = window.performance || {};" + 
                "var startTiming = performance.timing.navigationStart;"+
                "var endTiming = performance.timing.loadEventEnd;"+
                "return (endTiming - startTiming) + 'ms';"));
               
        Thread.sleep(5000);
        
        //Query and fill Username
        WebElement usernameQuery = driver.findElement(By.xpath(".//*[@id='emailId']"));
        usernameQuery.sendKeys("mockgeyser.slave@gmail.com");
        
        //Query and fill Password
        WebElement passwordQuery = driver.findElement(By.xpath(".//*[@id='password']"));
        passwordQuery.sendKeys("DASHBOARD1234");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}

