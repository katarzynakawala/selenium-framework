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
    private static final By ERROR_MESSAGE_LOCATOR = By.cssSelector("div.alert.alert-danger");


    public MyAccountPage doLoginWith(String emailAddress, String password) {
        log.info("Attempting login for user '{}'", emailAddress);
        enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        return myAccountPage;
    }

    public LoginPage LoginWithInvalidCredentials(String emailAddress, String password) {
        log.info("Attempting login for user with invalid credentials '{}'", emailAddress);
        enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
        return new LoginPage(getDriver());
    }

    public String getErrorMessage() {
        return getVisibleText(ERROR_MESSAGE_LOCATOR);
    }
}
