package com.ss.apitesting.util;

import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNgListeners implements ITestListener {

    private Logger log = LoggerFactory.getLogger("Listener");

    @Override
    public void onTestFailure(ITestResult result) {
        log.warn("The name of the testcase failed is: {}", result.getName());
        attachLogFile(result.getInstance().getClass().getSimpleName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("The name of the testcase Skipped is: {}", result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("{}.{} test case started", result.getInstance().getClass().getSimpleName(), result.getName());
        //TODO ITextContext - MDC.put("testname", .getClass().getSimpleName());
        // + onFinish() MDC.remove("testname");
        // + logs from REST-assured
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("The name of the testcase passed is: {}", result.getName());
    }

    @Attachment(value = "Logs for {className}", type = "text/plain", fileExtension = ".log")
    public byte[] attachLogFile(String className) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\target\\logs\\"
                    + className + ".log");
            return Files.readAllBytes(path);
        } catch (IOException ignored) {
            log.warn("Logs for " + className + " are unavailable");
        }
        return null;
    }
}
