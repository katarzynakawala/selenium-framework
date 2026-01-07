package com.ui.pages;

import com.constans.Browser;
import static com.constans.Env.*;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.JsonUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BrowserUtility {
    private static final Logger log =
            LoggerUtility.getLogger(HomePage.class);

    private static final By SIGN_IN_LINK_LOCATOR = By.cssSelector("a.login");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);
        log.info("Opening application home page");
        //goToWebsite(readProperty(QA, "URL"));
        goToWebsite(JsonUtility.getEnvironmentUrl(QA));
    }

    public HomePage(WebDriver driver) {
        super(driver);
        log.info("Opening application home page (LambdaTest)");
        //goToWebsite(readProperty(QA, "URL"));
        goToWebsite(JsonUtility.getEnvironmentUrl(QA));
    }

    public LoginPage goToLoginPage() {
        log.info("Navigating to Login page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }
}
