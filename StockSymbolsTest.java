package com.altruist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockSymbolsTest {

    WebDriver driver;
    List<String> expectedStockSymbols = Arrays.asList("NFLX", "MSFT", "TSLA", "AAPL", "GOOGL", "AMZN", "FB");

    @BeforeClass
    public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Initialize WebDriver
        driver = new ChromeDriver();

        // Open Google Finance page
        driver.get("https://www.google.com/finance");
    }

    @Test
    public void verifyStockSymbols() {
        // Verify the page title
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Google Finance"));

        // Retrieve stock symbols
        List<WebElement> stockElements = driver.findElements(By.cssSelector(".your-css-selector-here")); // Update with actual selector
        List<String> actualStockSymbols = new ArrayList<>();

        for (WebElement element : stockElements) {
            actualStockSymbols.add(element.getText());
        }

        // Compare stock symbols
        List<String> notInExpected = new ArrayList<>(actualStockSymbols);
        notInExpected.removeAll(expectedStockSymbols);

        List<String> notInActual = new ArrayList<>(expectedStockSymbols);
        notInActual.removeAll(actualStockSymbols);

        // Print results
        System.out.println("Stock symbols in actual but not in expected: " + notInExpected);
        System.out.println("Stock symbols in expected but not in actual: " + notInActual);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
