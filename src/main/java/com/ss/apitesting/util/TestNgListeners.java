package com.ss.apitesting.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgListeners implements ITestListener {

    private Logger log = LoggerFactory.getLogger("Listener");

    @Override
    public void onTestFailure(ITestResult result) {
        log.warn("The name of the testcase failed is: {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("The name of the testcase Skipped is: {}", result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("{} test case started", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("The name of the testcase passed is: {}", result.getName());
    }
}
