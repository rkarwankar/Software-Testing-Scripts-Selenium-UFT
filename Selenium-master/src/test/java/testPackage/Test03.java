package testPackage;

import static org.testng.Assert.assertEquals;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Suite")
@Feature("Class room Guide Download")
public class Test03 {
    
    private String testName = "Scenario 3";
    private int counter = 0;
    public static WebDriver driver;

    
   
    
    @Test
    @Story("Valid Login")
    @Description("Test the login functionality with valid credentials.")
    public void classroomGuide() throws InterruptedException {
    	WebDriver driver = Test01.driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://service.northeastern.edu/tech?id=classrooms");
        
        TimeUnit.SECONDS.sleep(2);

    	// Select a classroom
		WebElement classroomLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'007 Behrakis Health Sciences Center')]")));
		classroomLink.click();
		
		TimeUnit.SECONDS.sleep(2);
		String actualTitle = driver.getTitle();
        String expectedTitle = "Classroom Details - Northeastern Tech Service Portal";
        assertEquals(expectedTitle,actualTitle);
	
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);

		// Find and click the link that downloads the PDF
		WebElement guideLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ng-binding']")));
		TimeUnit.SECONDS.sleep(2);
		guideLink.click();
		TimeUnit.SECONDS.sleep(2);
		String pdfUrl = guideLink.getAttribute("href");
		downloadFile(pdfUrl);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
	}
	
	// Method to download a file from a given URL
	private void downloadFile(String fileUrl) {
	    try {
	        // Create a URL object
	        URL url = new URL(fileUrl);
	        // Open a connection to the URL
	        URLConnection connection = url.openConnection();
	        // Set the required properties for downloading
	        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
	        // Connect to the URL
	        connection.connect();
	        // Get the input stream from the connection
	        InputStream inputStream = connection.getInputStream();
	        // Create a buffered output stream to write the file
	        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("C:\\\\Users\\\\rkarw\\\\Downloads\\\\Hybrid_Nuflex_Classroom.pdf"));
	        // Define a buffer size for reading data from the input stream
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        // Read from the input stream and write to the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        // Close the output stream
	        outputStream.close();
	        // Close the input stream
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
} 