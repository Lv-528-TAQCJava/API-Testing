package com.ss.apitesting.util;

import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.slf4j.Logger;

/**
 * Will retry running a test for 3 times unless the response status code is 4** (400, 404 etc.)
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer{

    private int retryCount = 0;
    private int maxRetryCount = 3;
    private Logger log = LoggerFactory.getLogger("Retry");

    public boolean retry(ITestResult result) {
        if(result.getThrowable().getMessage().contains("but was <4")) {
            log.warn("Status code 4**, will not retry");
            return false;
        } else if(retryCount < maxRetryCount)
        {
            retryCount++;
            return true;
        } else {
            return false;
        }
    }
}
