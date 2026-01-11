package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SearchResultPage extends BrowserUtility {

    private static final By PRODUCT_LISTING_TITLE = By.xpath("//span[@class=\"lighter\"]");
    private static final By ALL_PRODUCT_LIST_NAME = By.xpath("//h5[@itemprop=\"name\"]/a");


    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    //TODO replace with explicit wait

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchResultTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LISTING_TITLE));

        return getVisibleText(PRODUCT_LISTING_TITLE);
    }

    public boolean isSearchedPresentInList(String searchTerm) {
        List<String> keywordsList = Arrays.asList(searchTerm.toLowerCase().split(" "));
        List<String> productNameList = getAllVisibleText(ALL_PRODUCT_LIST_NAME);

        return productNameList.stream()
                .anyMatch(name -> (keywordsList.stream().anyMatch(name.toLowerCase()::contains)));
    }
}
