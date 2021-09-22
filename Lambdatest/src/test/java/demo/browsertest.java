package demo;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class browsertest {



	public RemoteWebDriver driver = null;
	   String username = "ayshaareej116";
		String accessKey = "o4SFyZJgpltIUs0KCeKtTLBkgjUzCBGx03jU2cNP8ZCbODxmja";
		
		String gridURL = "@hub.lambdatest.com/wd/hub";
	    boolean status = false;
	    public static void main(String[] args) {
	        new browsertest().test();
	    }
	    public void test() {
	        // To Setup driver
	        setUp();
	        try {
	              //Change it to production page
	            driver.get("ht");
	             
	              //Let's mark done first two items in the list.
	              driver.findElement(By.name("li1")).click();
	            driver.findElement(By.name("li2")).click();
	             
	             // Let's add an item in the list.
	              driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
	            driver.findElement(By.id("addbutton")).click();
	             
	              // Let's check that the item we added is added in the list.
	            String enteredText = driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
	            if (enteredText.equals("Yey, Let's add it to list")) {
	                status = true;
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } finally {
	            tearDown();
	        }
	    }
	    private void setUp() {
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("browserName", "chrome");
	        capabilities.setCapability("version", "70.0");
	        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
	        capabilities.setCapability("build", "LambdaTestSampleApp");
	        capabilities.setCapability("name", "LambdaTestJavaSample");
	        try {
	            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
	        } catch (MalformedURLException e) {
	            System.out.println("Invalid grid URL");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    private void tearDown() {
	        if (driver != null) {
	            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
	            driver.quit(); //really important statement for preventing your test execution from a timeout.
	        }
	    }
}


