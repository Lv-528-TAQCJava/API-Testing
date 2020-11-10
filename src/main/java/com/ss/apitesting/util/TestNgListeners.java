package com.ss.apitesting.util;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgListeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result)
    {
        System.out.println("The name of the testcase failed is :"+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        System.out.println("The name of the testcase Skipped is :"+result.getName());
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        System.out.println(result.getName()+" test case started");
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        System.out.println("The name of the testcase passed is :"+result.getName());
    }


}
