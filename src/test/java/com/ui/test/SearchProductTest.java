package com.ui.test;

import com.ui.pages.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListener.class)
public class SearchProductTest extends TestBase {

    private MyAccountPage myAccountPage;
    private static final  String SEARCH_TERM = "Printed Summer Dress";

    @BeforeMethod(description = "Login to the app with valid user")
    public void setup(){
        myAccountPage = homePage.goToLoginPage().doLoginWith("toxar17447@mucate.com", "password123");
    }

    @Test(description = "verify that searching functionality works")
    public void verifyProductSearchTest() {
        boolean actualResult = myAccountPage.searchForAProduct(SEARCH_TERM).isSearchedPresentInList(SEARCH_TERM);
        Assert.assertTrue(actualResult);

    }
}
