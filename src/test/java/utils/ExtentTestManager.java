package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static final ExtentReports extent = ExtentReportManager.getExtentReports();

    // Parent test (TestNG test) – thread-safe
    private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();

    // Child test (Scenario)
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Store test name to reuse parent node across scenarios in same test
    private static final Map<String, ExtentTest> parentTestMap = new HashMap<>();

    public static void startTest(String testName, String scenarioName) {
        ExtentTest parent;
        synchronized (ExtentTestManager.class) {
            if (parentTestMap.containsKey(testName)) {
                parent = parentTestMap.get(testName);
            } else {
                parent = extent.createTest(testName);
                parentTestMap.put(testName, parent);
            }
        }

        // Always create a new node (even for retry)
        ExtentTest child = parent.createNode(scenarioName);
        parentTest.set(parent);
        test.set(child);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void removeTest() {
        test.remove();
        parentTest.remove();
    }
}
