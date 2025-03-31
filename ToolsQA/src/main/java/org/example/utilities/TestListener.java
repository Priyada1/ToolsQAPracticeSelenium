package org.example.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    // Shared ExtentReports instance
    private static ExtentReports extent = ExtentManager.getInstance();

    // ThreadLocal ensures each test runs in its own thread (for parallel execution)
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // Triggered when a test starts
    @Override
    public void onTestStart(ITestResult result) {
        // Create a test entry in Extent Reports with the test method name
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test); // Store in ThreadLocal
    }

    // Triggered when a test passes
    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test Passed ✅");
    }

    // Triggered when a test fails
    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().log(Status.FAIL, "Test Failed ❌: " + result.getThrowable());
    }

    // Triggered when a test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test Skipped ⚠️");
    }

    // Triggered when all tests are finished
    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Generate the final report
    }
}

