import java.io.*;
import java.util.*;
import java.util.Set;

import javax.script.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Set.*;

public class driver{ 
    public static void main(String[] args) throws Exception {
    	
    	try
    	{
    		
    	System.setProperty("webdriver.chrome.driver", "//Users/mindtap/Desktop/Selenium1/chromedriver");
   	
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("-incognito");
    	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    	WebDriver ghostdriver = new ChromeDriver(capabilities);
    	ghostdriver.get("https://login.cengage.com/");
    	Thread.sleep(5000);
        
        //Query and fill Username
        WebElement usernameQuery = ghostdriver.findElement(By.xpath(".//*[@id='emailId']"));
        usernameQuery.sendKeys("mockgeyser.slave@gmail.com");
        
        //Query and fill Password
        WebElement passwordQuery = ghostdriver.findElement(By.xpath(".//*[@id='password']"));
        passwordQuery.sendKeys("DASHBOARD1234");
        
        WebElement loginButton = ghostdriver.findElement(By.xpath(".//*[@id='loginFormId']/input[3]"));
        loginButton.click();
        
        Thread.sleep(5000);
        
        WebElement courseOpenLink1 = ghostdriver.findElement(By.className("viewDetailsBtn"));
        courseOpenLink1.click();
        
        Thread.sleep(5000);
        
        WebElement courseOpenLink2 = ghostdriver.findElement(By.className("gracePeriodBtn"));
        courseOpenLink2.click();
        
        Thread.sleep(5000);	
        
        ArrayList<String> tabs = new ArrayList<String> (ghostdriver.getWindowHandles());
        ghostdriver.switchTo().window(tabs.get(1)); 
        
        Thread.sleep(5000);
        
        WebElement chapterOpenLink1 = ghostdriver.findElement(By.className("lpn_thumbTitle"));
        chapterOpenLink1.click();
        
        Thread.sleep(5000);
        
        WebElement chapterOpenLink2 = ghostdriver.findElement(By.className("lpn_thumbTitle"));
        chapterOpenLink2.click();
        
        Thread.sleep(5000);
        
        WebElement flashcardOpen = ghostdriver.findElement(By.id("app_view_flash_cards"));
        flashcardOpen.click();
        
        Thread.sleep(5000);
        
        ghostdriver.switchTo().frame(ghostdriver.findElement(By.id("29_NB_Dock_IFrame")));
        
        WebElement nextFlashcard = ghostdriver.findElement(By.id("next-card"));
        nextFlashcard.click();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}