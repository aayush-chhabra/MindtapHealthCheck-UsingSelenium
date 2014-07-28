
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
    	
    	try
    	{
    	// The Firefox driver supports javascript  
        WebDriver driver = new FirefoxDriver();
    		
        // Go to the Google Suggest home page
        driver.get("http://qae-ng.cengage.com/static/nb/login.html");
        
        // Enter the query string "Cheese"
        WebElement usernameQuery = driver.findElement(By.id("_username_id"));
        usernameQuery.sendKeys("sanat.chugh@cengage.com");
        
        // Entry into Username
        WebElement passwordQuery = driver.findElement(By.id("_password_id"));
        passwordQuery.sendKeys("Cengage1");
        
        System.out.print("LOGIN PAGE LOADING : ");
        System.out.println(((JavascriptExecutor)driver).executeScript("var performance = window.performance || {};" + 
                "var startTiming = performance.timing.navigationStart;"+
                "var endTiming = performance.timing.loadEventEnd;"+
                "return (endTiming - startTiming) + 'ms';"));
        
        // Entry into Password`
        WebElement searchButton = driver.findElement(By.className("goButton"));
        searchButton.click();
        
        System.out.print("LOGGING IN : ");
        System.out.println(((JavascriptExecutor)driver).executeScript("var performance = window.performance || {};" + 
                "var startTiming = performance.timing.navigationStart;"+
                "var endTiming = performance.timing.loadEventEnd;"+
                "return (endTiming - startTiming) + 'ms';"));
        
        Thread.sleep(5000);
        
        WebElement bookOneLink = driver.findElement(By.xpath(".//*[@id='masters']/div[5]/div[3]/ul[1]/li[1]/div/div[4]/a"));
        bookOneLink.click();
        
        System.out.print("CLICKING ON A BOOK FROM MASTER AND LANDING UP IN MINDTAP : ");
        System.out.println(((JavascriptExecutor)driver).executeScript("var performance = window.performance || {};" + 
                "var startTiming = performance.timing.navigationStart;"+
                "var endTiming = performance.timing.domComplete;"+
                "if(endTiming===0){ endTiming  = performance.timing.loadEventEnd;}" +
                "if(endTiming===0){ endTiming  = performance.timing.domContentLoadedEventEnd;}" +
                "return (endTiming - startTiming) + 'ms';"));
        
        Thread.sleep(5000);
        
        WebElement chapterOneLink = driver.findElement(By.xpath(".//*[@id='lpn_list_area']/ul/li/div/div[2]/h3/a"));
        chapterOneLink.click();
        
        System.out.print("CLICKING ON A CHAPTER : ");
        System.out.println(((JavascriptExecutor)driver).executeScript("var performance = window.performance || {};" + 
                "var startTiming = performance.timing.requestStart;"+
                "var endTiming = performance.timing.responseEnd;"+
                "return (endTiming - startTiming) + 'ms';"));
        
        Thread.sleep(5000);
        
        WebElement chapterOneOpen = driver.findElement(By.xpath(".//*[@id='app_view_flash_cards']/img"));
        chapterOneOpen.click();
        
        System.out.print("OPENING FLASHCARDS: ");
        System.out.println(((JavascriptExecutor)driver).executeScript("var performance = window.performance || {};" + 
                "var startTiming = performance.timing.unloadEventStart;"+
                "var endTiming = performance.timing.unloadEventEnd;"+
                "var startTiming1 = performance.timing.requestStart;"+
                "var endTiming1 = performance.timing.responseStart;"+
                "var startTiming2 = performance.timing.domLoading;"+
                "var endTiming2 = performance.timing.domComplete;"+
                "var startTiming3 = performance.timing.domContentLoadedEventStart;"+
                "var endTiming3 = performance.timing.domContentLoadedEventEnd;"+
                "var startTiming4 = performance.timing.loadEventStart;"+
                "var endTiming4 = performance.timing.loadEventEnd;"+
                "return (endTiming - startTiming) + (endTiming1 - startTiming1) "
                + "+ (endTiming2 - startTiming2) + (endTiming3 - startTiming3) + (endTiming4 - startTiming4) + 'ms';"));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}


