package com.ui.test;

import static com.constans.Browser.*;
import com.ui.pages.HomePage;
import static org.testng.Assert.*;

import com.ui.pojos.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase {

    @Test(description = "Login with valid user - happy path", dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void loginTest(User user){
        assertEquals(homePage.goToLoginPage()
                .doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName(), "Test Test");
    }

    @Test(description = "Login with valid user - happy path", dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCSVDataProvider")
    public void loginCSVTest(User user){
        assertEquals(homePage.goToLoginPage()
                .doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName(), "Test Test");
    }

    @Test(description = "Login with valid user - happy path", dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginExcelDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void loginExcelTest(User user){
        assertEquals(homePage.goToLoginPage()
                .doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName(), "Test Test");
    }

    @Test(description = "Login with invalid user", dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginExcelDataProvider", retryAnalyzer = com.ui.listeners.RetryAnalyzer.class)
    public void invalidLogin(User user){
        assertEquals(homePage.goToLoginPage().
                LoginWithInvalidCredentials("kacper@gmail.com", "123").getErrorMessage(),"There is 1 error\n" +
                "Invalid password.");
    }
}
