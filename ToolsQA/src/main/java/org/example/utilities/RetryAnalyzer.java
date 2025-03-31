package org.example.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 2;
    private int count = 1;

    public boolean retry(ITestResult result) {
        if (count < retryCount) {
            count++;
            return true;
        }
        return false;
    }


}
