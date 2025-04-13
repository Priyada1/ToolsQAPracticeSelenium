package org.example.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FlakyRetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 6;
    private int count = 1;

    public boolean retry(ITestResult result) {
        if (count < retryCount) {
            count++;
            return true;
        }
        return false;
    }
}