import java.io.*;
import java.util.*;
import java.lang.Object.*;

import javax.script.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
 import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ResourceTiming{ 
    public static void main(String[] args) throws Exception {
    	
    	try
    	{	
    		System.setProperty("webdriver.chrome.driver", "//Users/mindtap/Desktop/Selenium1/chromedriver");
    		WebDriver driver = new ChromeDriver();
    		driver.get("http://www.google.com/xhtml");
            System.out.println(((JavascriptExecutor)driver).executeScript("var resourceList = window.performance.getEntriesByType('resource') || {};"+
            		"return resourceList[0];"));
//            		"return 'TotalTime: ' + (endTiming0 - startTiming0) + ' ms';"));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}

