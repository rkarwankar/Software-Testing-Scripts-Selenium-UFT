package testPackage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.io.File;
import org.testng.Assert;


@Epic("Suite")
@Feature("Dataset Download")
public class Test04 {
	
	private String testName = "Scenario 4";
	private int counter = 0;

private boolean isZipFileDownloadedSuccessfully() {
        String downloadPath = "C:\\\\Users\\\\rkarw\\\\Downloads";
        String fileName = "neu_bz616j95x.zip"; 
        
        File downloadedFile = new File(downloadPath, fileName);
        return downloadedFile.exists();
    }
	
	@Test
	@Story("Dataset Download")
	@Description("Downloading a dataset from Northeastern Library.")
	public void classroomGuide() throws InterruptedException {
		WebDriver driver = Test01.driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://onesearch.library.northeastern.edu/discovery/search?vid=01NEU_INST:NU&lang=en");
		
		TimeUnit.SECONDS.sleep(2);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
        
		// click on â€œDigital Repository Serviceâ€�
		WebElement digitalRepoLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='digital repository service']")));
		digitalRepoLink.click();
		
		for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
		}
		
		// Click on Quick Guide PDF link
		WebElement datasetsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/datasets']")));
		TimeUnit.SECONDS.sleep(1);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		datasetsLink.click();
		
		TimeUnit.SECONDS.sleep(1);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		
		// Select a classroom
		WebElement datasetLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/files/neu:ww72br59n']")));
		datasetLink.click();
		
		TimeUnit.SECONDS.sleep(1);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		
		WebElement zipFileLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Zip File']")));
		zipFileLink.click();
		
		TimeUnit.SECONDS.sleep(10);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		
		// Assertion to verify if the zip file download is successful
				Assert.assertTrue(isZipFileDownloadedSuccessfully(), "Zip file download failed!");

	}
	
	
	
}
