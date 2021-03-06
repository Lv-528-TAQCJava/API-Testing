package com.ss.apitesting.util;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNgListeners implements ITestListener {

    private Logger log = LoggerFactory.getLogger("Listener");

    @Override
    public void onTestFailure(ITestResult result) {
        log.warn("The name of the testcase failed is: {}", result.getName());
        attachLogFile(result.getInstance().getClass().getSimpleName());
        attachRequestLogFile(result.getName());
        //TODO use retry analyzer (do something with the result param)
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("The name of the testcase Skipped is: {}", result.getName());
        attachLogFile(result.getInstance().getClass().getSimpleName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("{}.{} test case started",
                result.getInstance().getClass().getSimpleName(), result.getName());
        //setFilters(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("The name of the testcase passed is: {}", result.getName());
    }

    //---------------------------------------------------------------------------------
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

    @Attachment(value = "Request logs for {testName}", type = "text/plain", fileExtension = ".log")
    public byte[] attachRequestLogFile(String testName) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\target\\logs\\"
                    + testName + "_requests.log");
            return Files.readAllBytes(path);
        } catch (IOException ignored) {
            log.warn("Request logs for " + testName + " are unavailable");
        }
        return null;
    }
/*
    public void setFilters(String testName) {
        File logFile = new File(System.getProperty("user.dir") + "\\target\\logs\\"
                + testName + "_requests.log");
        try {
            //logFile.createNewFile();
            PrintStream stream = new PrintStream(logFile);
            RestAssured.filters(
                    new ResponseLoggingFilter(LogDetail.ALL, stream),
                    new RequestLoggingFilter(LogDetail.ALL, stream)
            );
        } catch (IOException er) {
            log.warn("Can not set RestAssured filters' output to file, will log to console.");
            er.printStackTrace();
            RestAssured.filters(
                    new ResponseLoggingFilter(LogDetail.ALL),
                    new RequestLoggingFilter(LogDetail.ALL)
            );
        }
    }
*/
}
