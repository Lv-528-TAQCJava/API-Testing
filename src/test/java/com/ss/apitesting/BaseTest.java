package com.ss.apitesting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@Listeners(com.ss.apitesting.util.TestNgListeners.class)
public abstract class BaseTest {
    protected Logger log;

    protected abstract String getLoggerName();

    protected void initLogger() {
        log = LoggerFactory.getLogger(getLoggerName());
    }

    @BeforeClass
    public void beforeClass() {
        initLogger();
    }
}
