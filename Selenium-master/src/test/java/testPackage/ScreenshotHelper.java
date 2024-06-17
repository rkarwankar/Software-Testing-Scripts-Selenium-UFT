package testPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {

    public static void takeScreenshot(WebDriver driver, String testName, int counter) {
        // Convert WebDriver object to TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Capture screenshot as File
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Define the base directory for screenshots
        String baseScreenshotDir = "C:\\Users\\rkarw\\Desktop\\Selenium\\ScreenShots";

        // Create scenario-specific folder path
        String scenarioDir = Paths.get(baseScreenshotDir, testName).toString();
        File scenarioFolder = new File(scenarioDir);

        // Create scenario folder if it doesn't exist
        if (!scenarioFolder.exists()) {
            scenarioFolder.mkdirs();
        }

        // Define the destination file name
        String fileName = "Screenshot_for_" + testName + "_" + counter + ".png";
        String filePath = Paths.get(scenarioDir, fileName).toString();

        // Copy the captured screenshot to the destination file
        try {
            FileUtils.copyFile(sourceFile, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}