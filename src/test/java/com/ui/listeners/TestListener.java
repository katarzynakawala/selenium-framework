package com.ui.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExcelReaderUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.*;

import java.io.File;

public class TestListener implements ITestListener {

    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    private Logger getLogger(ITestResult result) {
        return LoggerUtility.getLogger(
                result.getTestClass().getRealClass()
        );
    }

    @Override
    public void onTestStart(ITestResult result) {
        Logger log = getLogger(result);
        log.info("STARTED: {}", result.getMethod().getMethodName());

        String description = result.getMethod().getDescription();
        if (description != null) {
            log.info("Description: {}", description);
        }

        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getLogger(result).info("PASSED: {}", result.getMethod().getMethodName());
        ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getLogger(result).error(
                "FAILED: {}",
                result.getMethod().getMethodName(),
                result.getThrowable()
        );

        ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

        Object testclass = result.getInstance();
        BrowserUtility browserUtility = ((TestBase)testclass).getInstance();
        String screenshotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
        ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getLogger(result).warn("SKIPPED: {}", result.getMethod().getMethodName());
        ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtility.getLogger(TestListener.class)
                .info("Test suite started: {}", context.getName());

        ExtentReporterUtility.setupSparkReporter("report.html");
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtility.getLogger(TestListener.class)
                .info("Test suite finished: {}", context.getName());
        ExtentReporterUtility.flushReport();
    }
}