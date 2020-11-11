package com.ss.apitesting;

import com.ss.apitesting.util.TestNgListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@Listeners(TestNgListeners.class)
public abstract class BaseTest {
    protected Logger log;

    protected abstract String getLoggerName();

    protected void initLogger() {
        log = LoggerFactory.getLogger(getLoggerName());
    }

    @BeforeClass
    public void beforeClass() {
        initLogger();
        MDC.put("testname", this.getClass().getSimpleName());
        /* ^ Allows logging each test class into separate file.
        The <key> name is defined at logback-test.xml under <discriminator> tag*/
    }

    @AfterClass
    public void afterClass() {
        initLogger();
        MDC.remove("testname"); //Do not forget, otherwise logging into separate files will not work
    }
}
