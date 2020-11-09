package com.ss.apitesting.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Will retry running a test fro 3 times unless the response status code is 4** (400, 404 etc.)
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer{

    private int retryCount = 0;
    private int maxRetryCount = 3;

    public boolean retry(ITestResult result) {
        if(result.getThrowable().getMessage().contains("but was <4")) {
            System.out.println("Status code 4**, will not retry");
            return false;
        }
        else if(retryCount < maxRetryCount)
        {
            retryCount++;
            return true;
        }
        return false;
    }
}
