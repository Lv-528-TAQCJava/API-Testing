package com.ss.apitesting.util;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Adds retry analyzer to each test method
 */
public class TestNgAnnotationTransformer implements IAnnotationTransformer  {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzerImpl.class);
    }
}
