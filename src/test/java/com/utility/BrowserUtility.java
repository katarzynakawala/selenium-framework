package com.utility;

import com.constans.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.getProperty;

public class BrowserUtility {

    private static final Logger logger =
            LoggerUtility.getLogger(BrowserUtility.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        BrowserUtility.driver.set(driver);
    }

//    public BrowserUtility(Browser browserName) {
//        if (browserName == Browser.CHROME) {
//            driver.set(new ChromeDriver());
//        } else if (browserName == Browser.SAFARI) {
//            driver.set(new SafariDriver());
//        } else if (browserName == Browser.EDGE) {
//            driver.set(new EdgeDriver());
//        } else if (browserName == Browser.FIREFOX) {
//            driver.set(new FirefoxDriver());
//        }
//    }

    protected WebDriverWait getWait() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
        return new WebDriverWait(driver.get(), Duration.ofSeconds(10));
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching Browser for " + browserName);

        if (browserName == Browser.CHROME) {
            if (isHeadless) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");

                driver.set(new ChromeDriver(options));
            } else {
                driver.set(new ChromeDriver());
            }

        } else if (browserName == Browser.SAFARI) {
            driver.set(new SafariDriver());
        } else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));
            } else {
                driver.set(new EdgeDriver());
            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=new");
                options.addArguments("disable-gpu");
                driver.set(new FirefoxDriver(options));
            } else {
                driver.set(new FirefoxDriver());
            }
        }
    }

    public void goToWebsite(String url) {
        driver.get().get(url);
    }

    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void enterText(By locator, String textToEnter) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(textToEnter);
    }

    public void clearText(By textBoxLocator) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
        logger.info("Element found and clearing the text box field");
        element.clear();
    }


    public void selectFromDropdown(By dropdownLocator, String optionToSelect) {
        logger.info("Finding element with the locator" + dropdownLocator);

        WebElement element =
                getWait().until(ExpectedConditions.visibilityOfElementLocated(dropdownLocator));
        Select select = new Select(element);
        logger.info("Selecting the option" + optionToSelect);
        select.selectByVisibleText(optionToSelect);

    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(keyToEnter);
    }

    public String getVisibleText(WebElement element) {
        logger.info("Return visible text" + element.getText());

        return element.getText();
    }


    public String getVisibleText(By locator) {
        logger.info("Finding Element with the locator" + locator);
        WebElement element =
                getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public List<String> getAllVisibleText(By locator) {
        logger.info("Finding all elements with locator" + locator);

        List<WebElement> elementList =
                getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

        logger.info("Elements found and printing the list of elements");

        List<String> visibleTextList = new ArrayList<String>();

        for (WebElement element : elementList) {
            visibleTextList.add(getVisibleText(element));
        }

        return visibleTextList;
    }

    public List<WebElement> getAllElements(By locator) {
        logger.info("Finding all elements with locator" + locator);

        List<WebElement> elementList =
                getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

        logger.info("Elements found and printing the list of elements");

        return elementList;
    }

    public String takeScreenShot(String name) {
        File src = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

        File dir = new File(System.getProperty("user.dir"), "screenshots");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("HH-mm-ss").format(new Date());
        String path = new File(dir, name + "-" + timeStamp + ".png").getPath();

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            logger.error("Unable to save screenshot: {}", path, e);
        }
        return path;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
