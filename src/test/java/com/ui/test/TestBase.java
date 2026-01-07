package com.ui.test;

import com.constans.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static com.constans.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLambdaTest;


    @Parameters({"browser", "isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Load the homepage of the website")
    public void setup(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLambdaTest,
            @Optional("true") boolean isHeadless, ITestResult result) {
        this.isLambdaTest = isLambdaTest;
        WebDriver lambdaDriver;

        if (isLambdaTest) {
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);

        } else {
            logger.info("Load the homepage of the website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (isLambdaTest) {
            LambdaTestUtility.quitSession();
        } else {
            BrowserUtility.quitDriver();
        }
    }
}
