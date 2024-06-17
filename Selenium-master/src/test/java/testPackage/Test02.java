package testPackage;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Suite")
@Feature("Add To-do on Canvas")
public class Test02 {
	
	private String testName = "Scenario 2";
	private int counter = 0;
	
	@Test(dataProvider="todoData")
	@Story("Canvas To-do")
	@Description("Adding a To-do item to Canvas Calendar.")
	public void addCanvasTodo(String title, String date, String time, String details) throws InterruptedException, IOException{
		WebDriver driver = Test01.driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://northeastern.instructure.com/");
		
		// Open Calendar
		WebElement calendarLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/calendar']")));
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		calendarLink.click();
//		TimeUnit.SECONDS.sleep(2);
//		
//		String actualTitle = driver.getTitle();
//        String expectedTitle = "Calender";
//        assertEquals(expectedTitle,actualTitle);
//		
		// Click on the Add Button
		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Create New Event']")));
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		addButton.click();
		
		// go to My To Do
		WebElement myTodo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='ui-id-5']")));
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		myTodo.click();
		
		// title field
		WebElement titleField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='planner_note_title']")));
		titleField.sendKeys(title);
		
		// date field
		WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='planner_note_date']")));
		dateField.clear();
		dateField.sendKeys(date);
		
		// time field
		WebElement toField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='planner_note_time']")));
		toField.clear();
		toField.sendKeys(time);
		
		// details field
		WebElement locationField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='details_textarea']")));
		locationField.sendKeys(details);
		
		TimeUnit.SECONDS.sleep(2);
		ScreenshotHelper.takeScreenshot(driver, testName, ++counter);
		
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();

	}
	
	@DataProvider(name = "todoData")
	public String[][] todoData() throws IOException {
		String[][] data = ReadCSVHelper.readDataFromCSV("C:\\Users\\rkarw\\Desktop\\Selenium\\to_do.csv");
		return data;
	}
}
