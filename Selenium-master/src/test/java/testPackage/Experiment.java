package testPackage;

import com.opencsv.CSVReader;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Experiment {

    public static void main(String[] args) throws IOException {
        

        TestNG testNG = new TestNG();

        // Create a list of XmlSuite
        List<XmlSuite> suites = new ArrayList<>();

        // Create an XmlSuite
        XmlSuite suite = new XmlSuite();
        suite.setName("All Test Suites");

        // Create an XmlTest
        XmlTest xmlTest = new XmlTest(suite);
        xmlTest.setName("All Tests");

        // Add test classes to the XmlTest
        xmlTest.getClasses().addAll(getXmlClasses("testPackage.Test01"));
        xmlTest.getClasses().addAll(getXmlClasses("testPackage.Test02"));
        xmlTest.getClasses().addAll(getXmlClasses("testPackage.Test03"));
        xmlTest.getClasses().addAll(getXmlClasses("testPackage.Test04"));
        xmlTest.getClasses().addAll(getXmlClasses("testPackage.Test05"));

        // Add the XmlSuite to the list
        suites.add(suite);

        // Set the list of suites to the TestNG instance
        testNG.setXmlSuites(suites);

        // Run the testNG instance
        testNG.run();
    }

    // Helper method to create an XmlClass for a given test class
    private static List<XmlClass> getXmlClasses(String className) {
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass(className));
        return classes;
    }
}