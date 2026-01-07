package com.ui.test;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestOld {

    public static void main(String[] args) {
        WebDriver wd = new ChromeDriver();

        BrowserUtility browserUtility = new BrowserUtility(wd);
        browserUtility.goToWebsite("http://www.automationpractice.pl");
        browserUtility.maximizeWindow();



        By signInLinkLocator = By.cssSelector("a.login");
        browserUtility.clickOn(signInLinkLocator);

        By emailTextBoxLocator = By.id("email");
        browserUtility.enterText(emailTextBoxLocator, "Kacper@gmail.com");

        By passwordTextBoxLocator = By.id("passwd");
        browserUtility.enterText(passwordTextBoxLocator,"ddfdfddd");

        By signInButtonLocator = By.id("SubmitLogin");
        browserUtility.clickOn(signInButtonLocator);
    }
}
