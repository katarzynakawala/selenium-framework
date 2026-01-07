package com.ui.pages;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BrowserUtility {

    private static final Logger log =
            LoggerUtility.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_LOGIN_BUTTON_LOCATOR = By.id("SubmitLogin");


    public MyAccountPage doLoginWith(String emailAddress, String password) {
        log.info("Attempting login for user '{}'", emailAddress);
        enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        return myAccountPage;
    }
}
