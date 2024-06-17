package testPackage;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Suite")
@Feature("Academic Calendar")
public class Test05  {
	private String testName = "Scenario 5";
	private int counter = 0;
	
	@Test
	@Story("Academic Calendar Reivew")
	@Description("View academic calendar and unselect graduate calendar.")
	public void academicCalendar() throws InterruptedException {
		WebDriver driver = Test01.driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		
		driver.get("https://student.me.northeastern.edu/resources");
		
		TimeUnit.SECONDS.sleep(3);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		
		// click on Academics, Classes and Registration
		WebElement academicsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Academics, Classes & Registration')]")));
        academicsLink.click();
        
        ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
        
        // click on the Academic Calendar
        WebElement academicCalendarLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Academic Calendar")));
        academicCalendarLink.click();
        
        // switch to academic calendar page
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        
        TimeUnit.SECONDS.sleep(1);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
        
        // click on the Academic Calendar link
        WebElement calendarLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://registrar.northeastern.edu/article/academic-calendar/']")));
        calendarLink.click();
        //This is an assertion 
        String actualTitle = driver.getTitle();
        String expectedTitle = "Academic Calendar - Office of the University Registrar at Northeastern University";
        assertEquals(expectedTitle,actualTitle);
        
        TimeUnit.SECONDS.sleep(1);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		
        WebElement selectCalendarIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='trumba.spud.6.iframe']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectCalendarIframe);
        driver.switchTo().frame(selectCalendarIframe);
        
        TimeUnit.SECONDS.sleep(1);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		
        // uncheck one checkbox from the Calendars section
        WebElement graduateCheckbox = driver.findElement(By.xpath("//input[@id='mixItem1']"));
        graduateCheckbox.click();
        
        TimeUnit.SECONDS.sleep(1);
        ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
        
        driver.switchTo().defaultContent();
        
        // scroll to page bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        // switch to the calendar iframe
        WebElement calendarIframe = driver.findElement(By.xpath("//iframe[@id='trumba.spud.2.iframe']"));
        driver.switchTo().frame(calendarIframe);
        
        ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
        
        // find the "Add to My Calendar" button at the bottom
        WebElement addCalendarButton = driver.findElement(By.xpath("//button//span[contains(text(), 'Add to My Calendar')]"));
        if (addCalendarButton.isDisplayed()) {
            System.out.println("Button 'Add to My Calendar' is present and visible!");
        } else {
            System.out.println("Button 'Add to My Calendar' is present but not visible!");
        }
        
        TimeUnit.SECONDS.sleep(2);
	}

}
