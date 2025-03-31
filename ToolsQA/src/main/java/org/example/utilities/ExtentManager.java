package org.example.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    // Singleton Method - Ensures only ONE instance is created
    public static ExtentReports getInstance() {
        if (extent == null) {  // If already created, return existing instance
            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
// ExtentSparkReporter is a reporting tool used in Extent Reports to generate HTML-based test reports in a visually rich and interactive format.
            // Set report title and name
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Regression Test Report");
            // Create ExtentReports instance and attach reporter
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Optional: Add system details
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "Chakrapani Priyadarshi");
        }
        return extent;
    }

}
