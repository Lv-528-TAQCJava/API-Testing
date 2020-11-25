package com.ss.apitesting;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;


public abstract class BaseTest {
    protected Logger log;

    protected abstract String getLoggerName();

    protected void initLogger() {
        log = LoggerFactory.getLogger(getLoggerName());
    }

    @BeforeClass
    public void beforeClass() {
        initLogger();
        String className = this.getClass().getSimpleName();
        MDC.put("testname", className);
        /* ^ Allows logging each test class into separate file.
        The <key> name is defined at logback-test.xml under <discriminator> tag*/
    }

    @AfterClass
    public void afterClass() {
        initLogger();
        MDC.remove("testname"); //Do not forget, otherwise logging into separate files will not work
    }

    @BeforeMethod
    public void setFilters(Method method) {
        File logFile = new File(System.getProperty("user.dir") + "\\target\\logs\\"
                + method.getName() + "_requests.log");
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

    @AfterMethod
    public void switchOffFilters() {
        RestAssured.replaceFiltersWith(new io.restassured.filter.log.ErrorLoggingFilter());
    }
}
