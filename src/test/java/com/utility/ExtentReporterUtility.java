package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporterUtility {

    private static final ExtentReports extentReports = new ExtentReports();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public static void setupSparkReporter(String reportName) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + File.separator + reportName);
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void flushReport() {
        extentReports.flush();
    }
}
