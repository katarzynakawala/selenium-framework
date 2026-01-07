package com.ui.test;

import com.constans.Browser;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;

public class LoginTest2 {

    public static void main(String[] args) {
        HomePage homePage = new HomePage(Browser.CHROME, true);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.doLoginWith("Kacper@gmail.com", "ddfdfddd");

    }
}
